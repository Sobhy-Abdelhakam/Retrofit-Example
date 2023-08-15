package dev.sobhy.retrofitexample.ui.screens.savednotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sobhy.retrofitexample.data.Repo
import dev.sobhy.retrofitexample.data.model.Article
import dev.sobhy.retrofitexample.ui.screens.home.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedViewModel : ViewModel() {
    private val _state = MutableStateFlow(SavedState())
    val state = _state.asStateFlow()

    private val repository = Repo()

    init {
        getSavedNews()
    }

    private fun getSavedNews() {
        viewModelScope.launch {
            repository.getNewsFromDb().collectLatest { list ->
                _state.update {
                    it.copy(list = list)
                }
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAllNew()
        }
    }

//    fun deleteAllArticleFromDb(){
//        state.value.list.onEach {
//            deleteArticle(it)
//        }
//    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            repository.deleteNew(article)
            article.isSaved = false
            //**********//
            HomeViewModel().getArticles()
        }
    }
}

data class SavedState(
    val list: List<Article> = emptyList()
)
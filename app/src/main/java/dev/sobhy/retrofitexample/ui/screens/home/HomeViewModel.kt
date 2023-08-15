package dev.sobhy.retrofitexample.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sobhy.retrofitexample.data.Repo
import dev.sobhy.retrofitexample.data.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val repository = Repo()

    init {
        getArticles()
    }

    fun getArticles() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val remoteList = repository.getNews("bitcoins")
            val localList = repository.getNewsFromDb().first()
            val viewedList = compareLocalAndRemoteAndReturnViewedList(remoteList, localList)
            _state.update {
                it.copy(isLoading = false, list = viewedList)
            }
        }
    }

    private fun compareLocalAndRemoteAndReturnViewedList(
        remoteList: List<Article>,
        localList: List<Article>?
    ): List<Article> {
        return remoteList.onEach { remoteArticle ->
            if (localList?.find { localArticle ->
                    localArticle.title == remoteArticle.title
                } != null) {
                remoteArticle.isSaved = true
            }
        }
    }

    fun saveArticle(article: Article, index: Int) {
        val newList = state.value.list
        newList[index].isSaved = true
        _state.update {
            it.copy(list = newList)
        }
        viewModelScope.launch {
            repository.insertNew(article)
        }
    }

    fun dismissDialog() {
        _state.update {
            it.copy(dialogModel = null)
        }
    }
}

data class HomeState(
    val isLoading: Boolean = true,
    val list: List<Article> = emptyList(),
    val dialogModel: DialogModel? = null
)

data class DialogModel(
    val message: String,
    val show: Boolean = false
)
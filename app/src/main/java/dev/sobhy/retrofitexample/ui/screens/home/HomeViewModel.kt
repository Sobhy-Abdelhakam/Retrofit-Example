package dev.sobhy.retrofitexample.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sobhy.retrofitexample.data.Repo
import dev.sobhy.retrofitexample.data.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            val list = repository.getNews("bitcoins")
            _state.update {
                it.copy(isLoading = false, list = list)
            }
        }
    }

    fun saveArticle(article: Article) {
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
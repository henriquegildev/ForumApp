package pt.ipbeja.forumapp.ui.viewpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewPostViewModel(val postId: Int) : ViewModel() {



    init {
        fetchPost()
    }


    fun fetchPost() {
        viewModelScope.launch(Dispatchers.IO) {
            // ... notify work in progress

            // ... val post = Api.getPost(postId) // TODO create the getPost function -> https://pdm-21-forum.duckdns.org/forum/posts/{post id}

            // ... notify work result
        }
    }



    class Factory(val postId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ViewPostViewModel(postId) as T
        }

    }
}
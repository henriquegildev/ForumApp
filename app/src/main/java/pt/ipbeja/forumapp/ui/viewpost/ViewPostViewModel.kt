package pt.ipbeja.forumapp.ui.viewpost

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pt.ipbeja.forumapp.remote.Api
import pt.ipbeja.forumapp.remote.Post
import pt.ipbeja.forumapp.resource.Resource

class ViewPostViewModel(val postId: Int) : ViewModel() {

    // Example with Kotlin's Flows
    private val _post : MutableStateFlow<Resource<Post>> = MutableStateFlow(Resource.Loading)
    val post : Flow<Resource<Post>> = _post

    // Example with LiveData
    private val _postLD : MutableLiveData<Resource<Post>> = MutableLiveData()
    val postLD : LiveData<Resource<Post>> = _postLD



    init {
        fetchPost()
    }


    fun fetchPost() {
        viewModelScope.launch(Dispatchers.IO) {
            // ... notify work in progress

            // ... val post = Api.getPost(postId) // TODO create the getPost function -> https://pdm-21-forum.duckdns.org/forum/post/{post id}

            // ... notify work result
            viewModelScope.launch(Dispatchers.IO) {
                _post.emit(Resource.Loading)
                _postLD.postValue(Resource.Loading)

                delay(1000) // simulating api request delay. delete
                val post = Api.getPost(postId)

                //val post = List(10) { Post(it + 1, "User $it", "Hello $it", it, it.toLong(), listOf())}
                _post.emit(Resource.Success(post))
                _postLD.postValue(Resource.Success(post))
            }

        }
    }



    class Factory(val postId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ViewPostViewModel(postId) as T
        }

    }
}
package pt.ipbeja.forumapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pt.ipbeja.forumapp.remote.Api
import pt.ipbeja.forumapp.remote.Post
import pt.ipbeja.forumapp.resource.Resource

class MainViewModel : ViewModel() {

    // Example with Kotlin's Flows
    private val _posts : MutableStateFlow<Resource<List<Post>>> = MutableStateFlow(Resource.Loading)
    val posts : Flow<Resource<List<Post>>> = _posts

    // Example with LiveData

    private val _postsLD : MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val postsLD : LiveData<Resource<List<Post>>> = _postsLD


    init {
        fetchPosts()
    }



    fun fetchPosts() {

        viewModelScope.launch(Dispatchers.IO) {
            _posts.emit(Resource.Loading)
            _postsLD.postValue(Resource.Loading)

            delay(1000) // simulating api request delay. delete
            val posts = Api.getAllPosts()

            //val posts = List(10) { Post(it + 1, "User $it", "Hello $it", it, it.toLong(), listOf())}
            _posts.emit(Resource.Success(posts))
            _postsLD.postValue(Resource.Success(posts))
        }
    }

}
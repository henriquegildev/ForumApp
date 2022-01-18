package pt.ipbeja.forumapp.resource

sealed class Resource<out T> {

    object Loading: Resource<Nothing>()

    data class Success<T>(val data: T) : Resource<T>()

}
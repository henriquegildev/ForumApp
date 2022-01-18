package pt.ipbeja.forumapp.remote

import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val text: String,
    val id: Int = 0,
    val user: String = "",
    val likes: Int = 0,
    val timestamp: Long = 0,
    val comments: List<Comment> = listOf()
)

@Serializable
data class Comment(
    val postId: Int,
    val body: String,
    val id: Int = 0,
    val user: String = "",
)

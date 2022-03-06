package pt.ipbeja.forumapp

import io.ktor.http.ContentType.Application.Json
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import pt.ipbeja.forumapp.remote.Api
import pt.ipbeja.forumapp.remote.Post

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addPostTest() = runBlocking {
        val post = Post("Terceiro post na nova Social Network! :D")
        Json.toString()
        Api.addPost(post)
    }
    @Test
    fun addLikeTest() = runBlocking {
        Api.addLike(6)
    }
    @Test
    fun addCommentTest() = runBlocking {
        Api.addComment(6, "Comment testing # HGIL")
    }
}
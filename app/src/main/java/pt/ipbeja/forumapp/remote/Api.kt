package pt.ipbeja.forumapp.remote

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

object Api {

    private const val BASE_URL = "https://pdm-21-forum.duckdns.org/forum"

    private const val TIME_OUT = 5_000

    private val client = HttpClient(Android) {

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Auth) {

            basic {
                credentials { BasicAuthCredentials("user", "pass") } // TODO user/pass
            }
        }

        // --- Optional ---

        /*install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor =>", message)
                }
            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }*/


    }

    suspend fun getAllPosts() : List<Post> = listOf(Post(id =1, text = "Hello world", user = "Diogo", likes = 3, comments = listOf(Comment(1, "Hello")))) // TODO get all posts -> https://pdm-21-forum.duckdns.org/forum/posts/

    // TODO Add the other requests:
    //  GET - getPost (get a single post by id) -> https://pdm-21-forum.duckdns.org/forum/posts/{postId}
    //  POST - addComment (add a comment to a Post) -> https://pdm-21-forum.duckdns.org/forum/comments -> Use the Comment model as the body (requires postId and text attributes)


    // Unsure
    //  POST - addLike (add a like to a Post) -> https://pdm-21-forum.duckdns.org/forum/likes -> Body is the postId

}
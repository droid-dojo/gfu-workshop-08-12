package com.example.gfu.myapplication.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.gfu.myapplication.cats.Cat
import com.example.gfu.myapplication.cats.api.ApiCat
import com.example.gfu.myapplication.cats.api.CatApi
import com.example.gfu.myapplication.cats.db.CatDatabase
import com.example.gfu.myapplication.cats.db.DatabaseCat
import com.example.gfu.myapplication.cats.db.DatabaseUser
import com.example.gfu.myapplication.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {

    val database = Room
        .databaseBuilder(application, CatDatabase::class.java, "photos.db")
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl("https://cataas.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: CatApi = retrofit.create()

    val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(
        value = HomeUiState(
            posts = emptyList(),
            loading = false
        )
    )


    init {
        loadPostsFromServer()
    }

    private fun loadPostsFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            val apiPosts = api.searchCats()

            apiPosts.forEach {
                database.cats().addCat(it.asDatabasePost())
            }
            loadPostsFromDatabase()
        }
    }


    private fun loadPostsFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.update {
                it.copy(loading = true)
            }

            val databasePosts = database.cats().getAllCats()

            if (databasePosts.isEmpty()) {
                loadPostsFromServer()
            } else {
                val mapped = databasePosts.map { it.asUiPost() }
                uiState.update {
                    it.copy(posts = mapped, loading = false)
                }
            }
        }
    }

    private fun DatabaseCat.asUiPost() = Cat(
        author = User(
            name = user.name,
            location = user.location,
            profileImage = url
        ),
        url = url,
        likes = likes,
        comments = comments
    )

    private fun ApiCat.asDatabasePost() = DatabaseCat(
        url = "https://cataas.com/cat/$id",
        likes = 0,
        comments = 0,
        user = DatabaseUser(name = "Cataas", location = tags.joinToString())
    )


    fun likeCat(cat: Cat) {
        viewModelScope.launch {
            val kadse = database.cats().findCat(cat.url)

            if (kadse != null) {
                database.cats().addCat(
                    post = kadse.copy(likes = kadse.likes + 1)
                )

                uiState.update { oldState ->
                    oldState.copy(
                        posts = oldState.posts
                            .map { oldPost ->
                                if (oldPost == cat) {
                                    oldPost.copy(likes = oldPost.likes + 1)
                                } else {
                                    oldPost
                                }
                            }
                    )
                }
            } else {
                Log.e("Foo", "Kadse nicht gefunden")
            }
        }
    }
}
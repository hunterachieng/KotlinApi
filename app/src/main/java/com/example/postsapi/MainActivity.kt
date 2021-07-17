package com.example.postsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    lateinit var rvPosts:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPosts =findViewById(R.id.rvPosts)

        getPosts()

    }
    fun getPosts(){
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = retrofit.getPosts()
        request.enqueue(object :Callback, retrofit2.Callback<List<Post>?>{
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                if(response.isSuccessful){
                    var posts = response.body()
                   var postsAdapter = PostsAdapter(baseContext, posts!!)
                    rvPosts.adapter = postsAdapter
                    rvPosts.layoutManager=LinearLayoutManager(baseContext)
                }
            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {

            }
        })
    }
}
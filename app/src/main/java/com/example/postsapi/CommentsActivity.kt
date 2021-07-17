package com.example.postsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    var postId = 0
    lateinit var tvPostTitle:TextView
    lateinit var tvPostBody:TextView
    lateinit var rvPosts2:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        rvPosts2= findViewById(R.id.rvPosts2)
        postId = intent.getIntExtra("POST_ID",0)
        castViews()
        getPost()
        getComments()
    }
    fun castViews(){
        tvPostTitle = findViewById(R.id.tvPostTitle)
        tvPostBody = findViewById(R.id.tvPostBody)
    }
    fun getPost(){
        if(postId == 0){
            Toast.makeText(baseContext,"Post not found",Toast.LENGTH_LONG).show()
            finish()
        }
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request= apiClient.getPost(postId)
        request.enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
               if(response.isSuccessful){
                   var post = response.body()!!

                       tvPostTitle.text = post.title
                       tvPostBody.text = post.body

               }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
               Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun getComments(){
        if(postId == 0){
            finish()
        }
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getComments(postId)
        request.enqueue(object :Callback<List<Comments>>{
            override fun onResponse(call: Call<List<Comments>>,response: Response<List<Comments>>) {
                if(response.isSuccessful){
                    var comments = response.body()
                    var commentsAdapter = CommentsAdapter(comments!!)
                    rvPosts2.adapter = commentsAdapter
                    rvPosts2.layoutManager=LinearLayoutManager(baseContext)
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
package com.example.postsapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter (var context: Context,var postList: List<Post>):RecyclerView.Adapter<PostsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_posts,parent,false)
        return PostsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        var currentPosts = postList.get(position)
        holder.tvTitle.text = currentPosts.title
        holder.tvId.text =currentPosts.id.toString()
        holder.tvBody.text = currentPosts.body
        holder.cvPosts.setOnClickListener {
            var intent = Intent(context,CommentsActivity::class.java)
            intent.putExtra("POST_ID",currentPosts.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
       return postList.size
    }
}
class PostsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var cvPosts = itemView.findViewById<CardView>(R.id.cvPosts)
    var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    var tvBody = itemView.findViewById<TextView>(R.id.tvBody)
    var tvId = itemView.findViewById<TextView>(R.id.tvId)
}
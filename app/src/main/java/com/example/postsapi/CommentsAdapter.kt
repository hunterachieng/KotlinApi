package com.example.postsapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CommentsAdapter(var commentsList:List<Comments>):RecyclerView.Adapter<CommentsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
       var itemView = LayoutInflater.from(parent.context).inflate(R.layout.comments_posts,parent,false)
        return CommentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
       var currentComments = commentsList.get(position)
        holder.tvName.text = currentComments.name
        holder.tvEmail.text = currentComments.email
        holder.tvCommentBody.text = currentComments.body
    }

    override fun getItemCount(): Int {
       return  commentsList.size
    }
}
class CommentsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tvName = itemView.findViewById<TextView>(R.id.tvName)
    var tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
    var tvCommentBody = itemView.findViewById<TextView>(R.id.tvCommentBody)
}
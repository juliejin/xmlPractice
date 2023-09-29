package com.example.xmlpractice.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.xmlpractice.data.model.Author
import com.example.xmlpractice.databinding.AuthorItemViewBinding

class AuthorListAdapter(private val onClickAction: (author: Author)->Unit): RecyclerView.Adapter<AuthorListAdapter.AuthorViewHolder>() {
    var authorList: List<Author> = listOf()

    fun submitData(authorList: List<Author>) {
        this.authorList = authorList
    }

    class AuthorViewHolder(val parent: ViewGroup, val itemBinding: AuthorItemViewBinding, private val onClickAction: (author: Author)->Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(author: Author) {
            itemBinding.authorName.text = author.id
            Glide.with(parent.context)
                .load(author.iconUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemBinding.authorIcon)
            itemView.setOnClickListener {
                onClickAction(author)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        return AuthorViewHolder(
            parent,
            AuthorItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickAction
        )
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.bind(authorList[position])
    }

    override fun getItemCount(): Int {
        return authorList.size
    }
}
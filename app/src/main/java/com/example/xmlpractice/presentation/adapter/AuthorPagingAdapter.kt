package com.example.xmlpractice.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.xmlpractice.data.model.Author
import com.example.xmlpractice.databinding.AuthorItemViewBinding

private val AUTHOR_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Author>() {
    override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean =
        oldItem == newItem
}
class AuthorPagingAdapter (private val onClickAction: (author: Author)->Unit): PagingDataAdapter<Author, AuthorPagingAdapter.AuthorViewHolder>(AUTHOR_DIFF_CALLBACK) {

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
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
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
                //parent.findNavController().navigate()
            }
        }
    }
}
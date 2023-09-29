package com.example.xmlpractice.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.xmlpractice.data.model.Author
import com.example.xmlpractice.databinding.FragmentAuthorDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorDetailsFragment: Fragment() {
    private var author: Author? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentAuthorDetailsBinding.inflate(inflater)
        val args = arguments?.getSerializable("author") as? Author ?: savedInstanceState?.getSerializable("author") as? Author
        this.author = args
        binding.authorName.text = author?.id
        Glide.with(this)
            .load(author?.iconUrl)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.authorIcon)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("author", author)
    }
}
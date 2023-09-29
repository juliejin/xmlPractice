package com.example.xmlpractice.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.xmlpractice.R
import com.example.xmlpractice.databinding.FragmentAuthorBinding
import com.example.xmlpractice.presentation.adapter.AuthorListAdapter
import com.example.xmlpractice.presentation.adapter.AuthorPagingAdapter
import com.example.xmlpractice.presentation.viewmodel.AuthorViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthorFragment: Fragment() {

    val authorViewModel: AuthorViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentAuthorBinding.inflate(inflater)
        val usepaging = true
        if (usepaging) {
            val adapter = AuthorPagingAdapter {
                val bundle = bundleOf("author" to it)
                val navController = findNavController()
                navController.navigate(R.id.authorDetailFragment, bundle)
            }
            binding.authorList.adapter = adapter
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    authorViewModel.authorPagingData.collect {
                        adapter.submitData(it)
                    }
                }
            }
            authorViewModel.fetchAuthorListPagination()
        } else {
            val adapter = AuthorListAdapter {
                val bundle = bundleOf("author" to it)
                val navController = findNavController()
                navController.navigate(R.id.authorDetailFragment, bundle)
            }
            binding.authorList.adapter = adapter
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    authorViewModel.authorLiveData.collect {
                        adapter.submitData(it)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            authorViewModel.fetchAuthorList()
        }
        return binding.root
    }

}
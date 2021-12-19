package com.example.finalproject.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentSearchBinding
import com.example.finalproject.ui.search.photoDetails.PhotoDetailsFragment
import com.google.android.material.transition.Hold

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = Hold()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.rvPhotosGrid
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (page > 1) {
                    Log.d("Load", "Page: $page")
                    searchViewModel.loadPhotos(page)
                }
            }
        })

        val recyclerViewAdapter =
            searchViewModel.items.value?.let {
                SearchViewAdapter(it, object : SearchViewAdapter.OnItemClickListener {
                    override fun onItemClick(id: String) {
                        val photoDetailsFragment = PhotoDetailsFragment()
                        val bundle = Bundle()
                        bundle.putString("photoId", id)
                        photoDetailsFragment.arguments = bundle

                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment_content_main, photoDetailsFragment)
                            .addToBackStack(null)
                            .commit()
                    }

                })
            }
        recyclerView.adapter = recyclerViewAdapter

        searchViewModel.items.observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter?.notifyItemInserted(0)
        })

        searchViewModel.loadPhotos(1)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
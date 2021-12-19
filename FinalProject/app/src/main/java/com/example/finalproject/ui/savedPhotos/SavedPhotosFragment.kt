package com.example.finalproject.ui.savedPhotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentGalleryBinding
import com.example.finalproject.ui.search.photoDetails.PhotoDetailsFragment
import java.net.URI

class SavedPhotosFragment : Fragment() {

    private lateinit var savedPhotosViewModel: SavedPhotosViewModel
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedPhotosViewModel =
            ViewModelProvider(this).get(SavedPhotosViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.rvSavedPhotosGrid
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        savedPhotosViewModel.items.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = SavedPhotosViewAdapter(it, object :
                SavedPhotosViewAdapter.OnItemClickListener {
                override fun onItemClick(uri: String) {
                    val photoViewFragment = PhotoViewFragment()
                    val bundle = Bundle()
                    bundle.putString("photoUri", uri)
                    photoViewFragment.arguments = bundle

                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, photoViewFragment)
                        .addToBackStack(null)
                        .commit()
                }
            })
        })

        savedPhotosViewModel.loadPhotos()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
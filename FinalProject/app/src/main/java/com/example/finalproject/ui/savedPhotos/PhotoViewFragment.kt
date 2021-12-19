package com.example.finalproject.ui.savedPhotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.finalproject.databinding.FragmentGalleryBinding
import com.example.finalproject.databinding.FragmentPhotoDetailsBinding
import com.example.finalproject.databinding.FullscreenImageViewBinding
import com.example.finalproject.ui.search.photoDetails.PhotoDetailsViewModel
import com.google.android.material.transition.MaterialContainerTransform

class PhotoViewFragment: Fragment() {
    private var _binding: FullscreenImageViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FullscreenImageViewBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val photoUri = arguments?.getString("photoUri")

        val photoView = binding.pvPhoto
        context?.let {
            Glide
                .with(it)
                .load(photoUri)
                .override(Target.SIZE_ORIGINAL)
                .into(photoView)
        }
        return root
    }
}
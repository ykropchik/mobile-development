package com.example.finalproject.ui.search.photoDetails

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentPhotoDetailsBinding
import com.example.finalproject.services.DownloadService
import com.example.finalproject.services.PermissionService
import com.example.finalproject.services.StorageService
import com.google.android.material.transition.MaterialContainerTransform

class PhotoDetailsFragment: Fragment() {
    private lateinit var photoDetailsViewModel: PhotoDetailsViewModel
    private var _binding: FragmentPhotoDetailsBinding? = null

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
        photoDetailsViewModel =
            ViewModelProvider(this).get(PhotoDetailsViewModel::class.java)


        _binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val photoId = arguments?.getString("photoId")
        val downloadService = context?.let { DownloadService(it) }
        val permissionService = PermissionService(context as Activity)

        if (photoId != null) {
            photoDetailsViewModel.getPhotoDetails(photoId)
        }

        val photo = binding.ivPhoto
        val authorAvatar = binding.ivAuthorAvatar
        val authorUsername = binding.tvAuthorUsername
        val authorName = binding.tvAuthorName
        val likes = binding.tvPhotoLikes
        val downloads = binding.tvPhotoDownloads
        val views = binding.tvPhotoViews
        val downloadFab = binding.fab

        val storageService = StorageService()
        val downloadedPhotosIdList = storageService.getDownloadedIdList()

        photoDetailsViewModel.details.observe(viewLifecycleOwner, Observer {
            context?.let { it1 ->
                Glide
                    .with(it1)
                    .load(it.photoSource.regular)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.drawable.side_nav_bar)
                    .into(photo)
            }

            context?.let { it1 ->
                Glide
                    .with(it1)
                    .load(it.user.profileImg.medium)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.drawable.side_nav_bar)
                    .into(authorAvatar)
            }

            authorUsername.text = it.user.username
            authorName.text = it.user.name
            likes.text = it.likes.toString()
            downloads.text = it.downloads.toString()
            views.text = it.views.toString()

            if(downloadedPhotosIdList.indexOf(it.id) != -1) {
                downloadFab.setImageResource(R.drawable.ic_baseline_check_24)
            } else {
                downloadFab.setImageResource(R.drawable.ic_baseline_save_alt_24)
            }
        })



        downloadFab.setOnClickListener {
            Log.d("FAB", "CLICK")
            if (permissionService.checkPermission()) {
                Log.d("FAB", "permissionService")
                if (photoId != null) {
                    downloadService?.startImageDownload(photoId)
                }
            } else {
                permissionService.requestPermission()
            }
        }

        return root
    }

}
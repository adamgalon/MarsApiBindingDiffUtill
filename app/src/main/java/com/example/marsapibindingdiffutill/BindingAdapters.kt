package com.example.marsapibindingdiffutill

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marsapibindingdiffutill.adapters.PhotoGridAdapter
import com.example.marsapibindingdiffutill.model.MarsPhoto
import com.example.marsapibindingdiffutill.ui.MarsPhotoStatus


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsStatus")
fun bindStatus(imageView: ImageView, status: MarsPhotoStatus?) {
    when (status) {
        MarsPhotoStatus.LOADING -> {
            imageView.setImageResource(R.drawable.ic_connection_error)
            imageView.visibility = View.VISIBLE
        }
        MarsPhotoStatus.DONE -> {
            imageView.visibility = View.GONE
        }
        MarsPhotoStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

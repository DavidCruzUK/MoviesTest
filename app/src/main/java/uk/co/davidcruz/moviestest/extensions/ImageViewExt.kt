package uk.co.davidcruz.moviestest.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import uk.co.davidcruz.moviestest.R

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadDetailUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_error_image)
        .into(this)
}
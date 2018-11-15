package com.adrian.speedrun.common

import android.content.res.Resources
import com.adrian.speedrun.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.adrian.speedrun.common.injection.GlideApp

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    GlideApp.with(context).load(url).placeholder(R.drawable.loading_anim).into(this)
}

val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
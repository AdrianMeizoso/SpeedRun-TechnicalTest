package com.adrian.speedrun.common.injection

import android.content.res.Resources
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    GlideApp.with(context).load(url).into(this)
}

val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
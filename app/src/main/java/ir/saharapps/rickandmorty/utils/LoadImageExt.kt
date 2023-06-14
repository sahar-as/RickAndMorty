package ir.saharapps.rickandmorty.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(
    imageAddress: String
){
    Glide.with(context)
        .load(imageAddress)
        .into(this)
}
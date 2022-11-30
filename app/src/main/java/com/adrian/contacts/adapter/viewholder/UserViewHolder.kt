package com.adrian.contacts.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.adrian.contacts.R

class UserViewHolder(var v:View): RecyclerView.ViewHolder(v) {

    val userImg = v.findViewById<ShapeableImageView>(R.id.reImage)
    val userName = v.findViewById<TextView>(R.id.reName)
    val userMob = v.findViewById<TextView>(R.id.reNumber)
    val editView = v.findViewById<ImageView>(R.id.editView)
    val deleteBtn = v.findViewById<ImageView>(R.id.deleteBtn)


}
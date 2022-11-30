package com.adrian.contacts.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDatas(
    val id:String,
    val name:String,
    val imageUr:String,
    val mobN:String,
    val age:String,
    val email:String,
    val more:String
) : Parcelable {
    internal constructor(
        name:String,
        imageUr:String,
        mobN:String,
        age:String,
        email:String,
        more:String
    ) :this (
        id = "", name = name, imageUr = imageUr, mobN = mobN,
        age = age, email = email, more = more
    ){}


    internal constructor(
        name:String,
        imageUr:String,
        mobN:String
    ) :this (
        id = "",
        name = name,
        imageUr = imageUr,
        mobN = mobN,
        age = "",
        email = "",
        more = ""
    ){}

}
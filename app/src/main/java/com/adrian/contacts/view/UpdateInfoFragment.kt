package com.adrian.contacts.view

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.adrian.contacts.R
import com.adrian.contacts.model.HelperSQL
import com.adrian.contacts.model.UserDatas
import kotlinx.android.synthetic.main.fragment_add_info.*
import kotlinx.android.synthetic.main.fragment_add_info.MobileNo
import kotlinx.android.synthetic.main.fragment_add_info.dataBirth
import kotlinx.android.synthetic.main.fragment_add_info.email
import kotlinx.android.synthetic.main.fragment_add_info.imageUser
import kotlinx.android.synthetic.main.fragment_add_info.userName
import kotlinx.android.synthetic.main.fragment_update_info.*

class UpdateInfoFragment : Fragment() {

    //calling the database
    lateinit var dataHelper:HelperSQL

    var imageUri:Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise(view)
    }
    fun initialise(v:View){
        /**set Database*/
        dataHelper = HelperSQL(v.context)
        /**set Images*/
        imageUser.setOnClickListener {
            getImages1(it)
        }
        /**Adding Information*/
        updateInfo.setOnClickListener {
            sendData1(it)
        }
        /**set cancel button*/
        cancelBtn2.setOnClickListener {
            val ac = UpdateInfoFragmentDirections.UpdateToUserList()
            Navigation.findNavController(v).navigate(ac)
        }
    }

    private fun sendData1(v: View?) {
        if (userName1.text!!.isNotEmpty()) {
            val ac = UpdateInfoFragmentDirections.UpdateToUserList()
            Navigation.findNavController(v!!).navigate(ac)
            val newUser = UserDatas(
                userName1.text.toString(),
                "" + imageUri,
                MobileNo1.text.toString(),
                dataBirth1.text.toString(),
                email1.text.toString(),
                updateinfo.text.toString())

            val status = dataHelper.updateInfo(newUser)
            if (status >-1) {
                dataHelper.listUser()
                Toast.makeText(v.context, "Contact was updated successfully", Toast.LENGTH_SHORT)
                    .show()
            }else{
                Toast.makeText(v.context, "Contact update failed,check your input", Toast.LENGTH_SHORT)
                    .show()
            }
            } else{
            Toast.makeText(v!!.context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getImages1(v: View?) {
        /**open Images*/
        val openImage = AlertDialog.Builder(v!!.context)
        openImage.setTitle("Take picture from.")
        /**set icon*/
        openImage.setIcon(R.drawable.ic_perm)
        openImage.setPositiveButton("Camera"){
                dialog,_->
            getCamera1(v)
            dialog.dismiss()
        }
        openImage.setNegativeButton("Gallery"){
                dialog,_->
            getGallery1()
            dialog.dismiss()
        }
        openImage.setNeutralButton("Cancel"){
                dialog,_->
            dialog.dismiss()
        }
        openImage.create()
        openImage.show()

    }


    /**set Gallery*/
    private fun getGallery1() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent,2)

    }

    /**set Camera*/
    private fun getCamera1(v:View) {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE,"imageTitle")
        values.put(MediaStore.Images.Media.DESCRIPTION,"ImagesDescription")
        imageUri = v.context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)!!
        val cameraI = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraI.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
        startActivityForResult(cameraI,2)

    }

    override fun onDestroy() {
        super.onDestroy()
        dataHelper.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (data != null){
                imageUri = data.data
                imageUser.setImageURI(imageUri)

            }else{
                imageUser.setImageURI(imageUri)
            }
        }
    }


}
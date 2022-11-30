package com.adrian.contacts.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adrian.contacts.R
import com.adrian.contacts.model.UserDatas
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var passData : UserDatas
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UserDataPass()
    }
    private fun UserDataPass() {
        arguments?.let {
            passData = DetailFragmentArgs.fromBundle(it).userDatas!!
            /**set images*/
            if (passData.imageUr == "null") {
                mUser.setImageResource(R.drawable.ic_perm)
            } else {
                mUser.setImageURI(Uri.parse(passData.imageUr))
            }
            /**set Text*/
            nameUser.text = "Name : ${passData.name}"
            mob.text = "Mb. : ${passData.mobN}"
            age.text = "BirthData : ${passData.age}"
            emailU.text = "Email Id : ${passData.email}"
            moreU.text = passData.more
        }
    }
}

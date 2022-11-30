package com.adrian.contacts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.contacts.R
import com.adrian.contacts.adapter.UserListAdapter
import com.adrian.contacts.model.HelperSQL
import kotlinx.android.synthetic.main.fragment_user_list.*


class UserListFragment : Fragment() {


    private lateinit var helperData:HelperSQL

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addInfo(view)
    }
    fun addInfo(v:View){
        addBtn.setOnClickListener {
            val ac = UserListFragmentDirections.UserToAddInfo()
            Navigation.findNavController(it).navigate(ac)
        }

        /**Call Adapters*/
        attchAdapter(v)

    }


    private fun attchAdapter(v: View) {
        helperData = HelperSQL(v.context)
        userRecyclerView.layoutManager = LinearLayoutManager(v.context)
        userRecyclerView.setHasFixedSize(true)
        val allData = helperData.listUser()
        if (allData.size>0){
            userRecyclerView.visibility = View.VISIBLE
            tvUser.visibility = View.GONE
            val mAdapter = UserListAdapter(v.context,allData)
            userRecyclerView.adapter = mAdapter
        }
        else{
            userRecyclerView.visibility = View.GONE
            tvUser.visibility = View.VISIBLE
        }

    }


}
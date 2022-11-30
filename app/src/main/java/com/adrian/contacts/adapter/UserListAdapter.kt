package com.adrian.contacts.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.adrian.contacts.R
import com.adrian.contacts.adapter.viewholder.UserViewHolder
import com.adrian.contacts.model.HelperSQL
import com.adrian.contacts.model.UserDatas
import com.adrian.contacts.view.AddInfoFragmentDirections
import com.adrian.contacts.view.UserListFragmentDirections
import kotlinx.android.synthetic.main.fragment_add_info.*

class UserListAdapter(
    private val c:Context,listUser:ArrayList<UserDatas>
):RecyclerView.Adapter<UserViewHolder>()
{
    /** ok run it */

    private val listUs:ArrayList<UserDatas>
    private val mDataBase:HelperSQL
    init {
        this.listUs = listUser
        mDataBase = HelperSQL(c)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val infalter = LayoutInflater.from(parent.context)
        val v = infalter.inflate(R.layout.user_item,parent,false)
        return UserViewHolder(v)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = listUs[position]
        holder.userName.text = newList.name
        holder.userMob.text = newList.mobN
        if (newList.imageUr == "null"){
            holder.userImg.setImageResource(R.drawable.ic_perm)
        }else{
            holder.userImg.setImageURI(Uri.parse(newList.imageUr))
        }
        holder.v.setOnClickListener {
            val ac = UserListFragmentDirections.UserListToDetail().setUserDatas(newList)
            Navigation.findNavController(it).navigate(ac)
        }
        holder.deleteBtn.setOnClickListener {
            mDataBase.deleteInfo(newList.id.toInt())
            listUs.removeAt(position)
            notifyDataSetChanged()
        }
        holder.editView.setOnClickListener {
            val ac = UserListFragmentDirections.UserToUpdateInfo()
            Navigation.findNavController(it).navigate(ac)
        }

    }


    override fun getItemCount(): Int {
        return  listUs.size
    }
}

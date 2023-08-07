package com.desire.figmaauthpractice.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.desire.figmaauthpractice.databinding.RcvusersBinding

class UserAdapter (val userArrayList: MutableList<User>)
    : RecyclerView.Adapter<UserAdapter.rcvViewHolder>() {

    var onAllUserClickListener : OnClickListener? = null

    fun clickListener(listener : OnClickListener){
        onAllUserClickListener = listener
    }
    interface OnClickListener{
        fun onDeleteClick(index: Int, user: User)
    }

    inner class rcvViewHolder(var view: RcvusersBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(user: User, index : Int) {
            view.userId.text = user.id.toString()
            view.userName.text = user.name
            view.userEmail.text = user.emailId
            view.userPass.text = user.password
            view.btnDelete.setOnClickListener {
                onAllUserClickListener?.onDeleteClick(index,user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rcvViewHolder {
        var view: RcvusersBinding =
            RcvusersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return rcvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    override fun onBindViewHolder(holder: rcvViewHolder, position: Int) {
        holder.bind(userArrayList[position],position)
    }

    fun deleteItem(index: Int, user: User) {
        userArrayList.removeAt(index)
        notifyDataSetChanged()

    }
}
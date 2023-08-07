package com.desire.figmaauthpractice.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desire.figmaauthpractice.databinding.RcvlistBinding

class RcvAdapter(val dataArrayList: ArrayList<RcvModel>) :
    RecyclerView.Adapter<RcvAdapter.rcvViewHolder>() {

    fun deleteItem(index: Int) {
        dataArrayList.removeAt(index)
        notifyDataSetChanged()
    }

    var onItemClick: ((RcvModel) -> Unit)? = null

    inner class rcvViewHolder(var view: RcvlistBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(rcvModel: RcvModel, position: Int) {
            view.rcvName.text = rcvModel.category
            view.rcvDiscription.text = rcvModel.description
            Glide.with(view.rcvImage).load(rcvModel.image).into(view.rcvImage)
            view.btnDelete.setOnClickListener {
                onItemClick?.invoke(dataArrayList.get(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rcvViewHolder {
        var view: RcvlistBinding =
            RcvlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return rcvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataArrayList.size
    }

    override fun onBindViewHolder(holder: rcvViewHolder, position: Int) {
        holder.bind(dataArrayList[position],position)
    }

}

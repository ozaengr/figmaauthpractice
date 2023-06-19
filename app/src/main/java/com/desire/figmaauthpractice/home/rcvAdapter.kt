package com.desire.figmaauthpractice.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desire.figmaauthpractice.databinding.RcvlistBinding

class rcvAdapter(
    val dataArrayList: ArrayList<rcvModel>
) : RecyclerView.Adapter<rcvAdapter.rcvViewHolder>() {


    class rcvViewHolder(var view: RcvlistBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(rcvModel: rcvModel) {
            view.rcvName.text = rcvModel.category
            view.rcvDiscription.text = rcvModel.description
            Glide.with(view.rcvImage).load(rcvModel.image).into(view.rcvImage)
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
        holder.bind(dataArrayList[position])
    }

}

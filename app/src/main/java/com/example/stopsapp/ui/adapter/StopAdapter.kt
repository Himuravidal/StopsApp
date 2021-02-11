package com.example.stopsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.stopsapp.databinding.StopItemBinding
import com.example.stopsapp.model.local.StopData

class StopAdapter : RecyclerView.Adapter<StopAdapter.StopVH>() {

    var listStop = listOf<StopData>()
    private val selectedItem = MutableLiveData<StopData>()

    fun updateData(list: List<StopData>) {
        listStop = list
        notifyDataSetChanged()
    }

    fun selectedItem(): LiveData<StopData> {
        return selectedItem
    }

    inner class StopVH(private val binding: StopItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun binding(item: StopData) {
            binding.tvId.text = item.stopId
            binding.tvStopName.text = item.stopName
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedItem.value = listStop[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopVH {
        return StopVH(StopItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: StopVH, position: Int) {
        holder.binding(listStop[position])
    }

    override fun getItemCount() = listStop.size

}
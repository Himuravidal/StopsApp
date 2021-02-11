package com.example.stopsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.stopsapp.databinding.RouteItemBinding
import com.example.stopsapp.model.local.RouteData
import com.example.stopsapp.model.local.StopData

class RouteAdapter : RecyclerView.Adapter<RouteAdapter.RouteVH>() {

    var listRoute = listOf<RouteData>()
    private val selectedItem = MutableLiveData<StopData>()

    fun updateData(list: List<RouteData>) {
        listRoute = list
        notifyDataSetChanged()
    }

    inner class RouteVH(private val binding: RouteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(item: RouteData) {
            binding.tvBusCode.text = item.routeId
            binding.tvName.text = item.routeLongName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteVH {
        return RouteVH(RouteItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RouteVH, position: Int) {
        holder.binding(listRoute[position])
    }

    override fun getItemCount() = listRoute.size
}
package com.example.stopsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stopsapp.AppStop
import com.example.stopsapp.R
import com.example.stopsapp.databinding.FragmentFirstBinding
import com.example.stopsapp.ui.adapter.StopAdapter
import com.example.stopsapp.viewModel.StopViewModel
import com.example.stopsapp.viewModel.StopViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel : StopViewModel by activityViewModels {
        StopViewModelFactory((activity?.application as AppStop).repository)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = StopAdapter()
        setAdapter(adapter)
    }

    private fun setAdapter(adapter: StopAdapter) {
        setRecyclerView(adapter)
        selectItemFromAdapter(adapter)
        viewModel.currentStopDataFlow.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateData(it)
            }
        })
    }

    private fun setRecyclerView(adapter: StopAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context,
            DividerItemDecoration.VERTICAL))
    }

    private fun selectItemFromAdapter(adapter: StopAdapter) {
        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            viewModel.fetchRouteData(it.stopId)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        })
    }
}
package com.example.stopsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stopsapp.AppStop
import com.example.stopsapp.R
import com.example.stopsapp.databinding.FragmentSecondBinding
import com.example.stopsapp.ui.adapter.RouteAdapter
import com.example.stopsapp.viewModel.StopViewModel
import com.example.stopsapp.viewModel.StopViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: StopViewModel by activityViewModels {
        StopViewModelFactory((activity?.application as AppStop).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RouteAdapter()
        setAdapter(adapter)
        observeErrors()
    }

    private fun setAdapter(adapter: RouteAdapter) {
        setRecyclerView(adapter)
        viewModel.getRouteSelectedFromDB().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateData(it)
            }
        })
    }

    private fun setRecyclerView(adapter: RouteAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun observeErrors() {
        val errorMsg = getString(R.string.erroMsg)
        viewModel.getErrorFromFetch().observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context, it + errorMsg, Toast.LENGTH_LONG).show()
            }
        })
    }

}
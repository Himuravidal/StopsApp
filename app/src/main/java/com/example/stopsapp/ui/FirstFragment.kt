package com.example.stopsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.stopsapp.AppStop
import com.example.stopsapp.R
import com.example.stopsapp.databinding.FragmentFirstBinding
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

        viewModel.currentStopDataFlow.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.textviewFirst.text = it.toString()
            }
        })

    }
}
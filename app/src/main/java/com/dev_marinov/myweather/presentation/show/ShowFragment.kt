package com.dev_marinov.myweather.presentation.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev_marinov.myweather.R
import com.dev_marinov.myweather.databinding.FragmentShowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowFragment : Fragment() {

    private lateinit var binding: FragmentShowBinding
    private val viewModel: ShowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayout()
    }

    private fun setLayout() {

        val adapter = ShowAdapter(viewModel::onClick)
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            this.adapter = adapter
        }

        viewModel.cities.observe(viewLifecycleOwner) {
            it?.let { towns ->
                adapter.submitList(towns)
            }
        }

        viewModel.uploadData.observe(viewLifecycleOwner) {
            navigateToDetailFragment(it)
        }

    }

    private fun navigateToDetailFragment(id: String) {
        val action = ShowFragmentDirections.actionShowFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }
}
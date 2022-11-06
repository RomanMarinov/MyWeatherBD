package com.dev_marinov.myweather.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev_marinov.myweather.R
import com.dev_marinov.myweather.databinding.FragmentDetailBinding
import com.dev_marinov.myweather.domain.Detail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayout()
    }

    private fun setLayout() {
        val adapter = DetailAdapter()
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            this.adapter = adapter
        }

        viewModel.getDetail(args.id)

        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailCity.collect {
                    adapter.submitList(it)
                    it.map { detail ->
                        setTitleLayout(detail)
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.status.collect { Boolean ->
                    if (Boolean) {
                        viewModel.viewState.observe(viewLifecycleOwner) {
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        }
                        viewModel.status.value = false
                    }
                }
            }
        }
    }

    private fun setTitleLayout(details: Detail) {
        binding.tvLastDateHead.visibility = View.VISIBLE
        binding.tvCity.text = details.city
        binding.tvLastDate.text = details.time?.substring(0, 11)
    }
}
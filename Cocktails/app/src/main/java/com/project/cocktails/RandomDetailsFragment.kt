package com.project.cocktails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.cocktails.databinding.FragmentNonAlcDetailsBinding
import com.project.cocktails.databinding.FragmentRandomDetailsBinding
import com.project.cocktails.viewmodel.NonAlcDetailViewModel
import com.project.cocktails.viewmodel.RandomDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class RandomDetailsFragment : Fragment() {
    val viewModel by viewModel<RandomDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_random_details, container, false)

        val binding = FragmentRandomDetailsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val nonAlcProperty = RandomDetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty
        binding.viewmodel = viewModel
        viewModel.setProperty(nonAlcProperty)

        return binding.root


    }


}
package com.project.cocktails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.project.cocktails.databinding.FragmentNonAlcDetailsBinding
import com.project.cocktails.viewmodel.NonAlcDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class NonAlcDetailsFragment : Fragment() {
    val viewModel by viewModel<NonAlcDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_non_alc_details, container, false)
        //Read data from bundle

        val binding = FragmentNonAlcDetailsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val nonAlcProperty = NonAlcDetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty
        binding.viewmodel = viewModel
        viewModel.setProperty(nonAlcProperty)

        return binding.root

    }

}
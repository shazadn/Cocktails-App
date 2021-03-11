package com.project.cocktails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.cocktails.databinding.FragmentCocktailDetailsBinding
import com.project.cocktails.viewmodel.CocktailDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CocktailDetailsFragment : Fragment() {

    val viewModel by viewModel<CocktailDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_cocktail_details, container, false)
        val binding = FragmentCocktailDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        //Read data from bundle
        val cocktailProperty = CocktailDetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty
        binding.viewmodel = viewModel
        viewModel.setProperty(cocktailProperty)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().findViewById<View>(R.id.navigation_viewer).apply {
            visibility = View.INVISIBLE
        }
    }
}
package com.project.cocktails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.cocktails.databinding.FragmentFirstBinding
import com.project.cocktails.domain.Cocktail
import com.project.cocktails.recycler_adapters.CocktailClick
import com.project.cocktails.recycler_adapters.DrinksAdapter
import com.project.cocktails.viewmodel.CocktailViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FirstFragment : Fragment() {
    private lateinit var drinksAdapter: DrinksAdapter
    private val viewModel by viewModel<CocktailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
        val binding: FragmentFirstBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first, container, false
        )

        binding.viewmodel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        setupObservers()
    }

    private fun setupRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.cocktail_rv).apply {
            drinksAdapter = DrinksAdapter(CocktailClick {
                viewModel.displayPropertyDetails(it)
            })

            adapter = drinksAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    private fun setupObservers() {
        viewModel.cocktailResults.observe(viewLifecycleOwner, Observer<List<Cocktail>> { drink ->
            drink?.apply {
                drinksAdapter.results = drink
                Toast.makeText(context, "Working", Toast.LENGTH_LONG).show()
            }
        })

        // navigate as well
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            it?.apply {
                Log.d("Data ", it.idDrink.toString())

                findNavController().navigate(
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(it)
                )

                requireActivity().findViewById<View>(R.id.navigation_viewer).apply {
                    visibility = View.VISIBLE
                }

                viewModel.displayPropertyDetailsComplete()
            }
        })
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): FirstFragment {
            return FirstFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}

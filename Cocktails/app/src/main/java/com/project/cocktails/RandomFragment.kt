package com.project.cocktails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.cocktails.databinding.FragmentRandomBinding
import com.project.cocktails.domain.Random
import com.project.cocktails.recycler_adapters.NonAlcClick
import com.project.cocktails.recycler_adapters.NonAlcoholAdapter
import com.project.cocktails.recycler_adapters.RandomAdapter
import com.project.cocktails.recycler_adapters.RandomClick
import com.project.cocktails.viewmodel.RandomViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RandomFragment : Fragment() {


    private var viewModelAdapter: RandomAdapter? = null

    private val viewModel by viewModel<RandomViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_random, container, false)

        val binding: FragmentRandomBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_random, container, false
        )

        binding.randomviewmodel = viewModel
        viewModelAdapter = RandomAdapter(RandomClick {
            viewModel.displayPropertyDetails(it)
        })


                // navigate as well
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                Log.d("Data ", it.strDrink)
                this.findNavController().navigate(
                 RandomFragmentDirections.actionRandomToRandomDetails(it)

                )
                viewModel.displayPropertyDetailsComplete()
            }
        })
        binding.root.findViewById<RecyclerView>(R.id.random_rv).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserverToGetData()
    }

    private fun setUpObserverToGetData() {
        viewModel.randomResults.observe(viewLifecycleOwner, Observer<List<Random>> { drink ->
            drink?.apply {
                viewModelAdapter?.results = drink
//                Toast.makeText(context, "Working", Toast.LENGTH_LONG).show()
//                Log.i("Random Result: ", drink.get(3).strDrink)

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
        fun newInstance(sectionNumber: Int): RandomFragment {
            return RandomFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
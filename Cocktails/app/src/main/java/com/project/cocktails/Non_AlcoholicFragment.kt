package com.project.cocktails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.cocktails.databinding.NonAlcoholicFragmentBinding
import com.project.cocktails.domain.NonAlcohol
import com.project.cocktails.recycler_adapters.NonAlcClick
import com.project.cocktails.recycler_adapters.NonAlcoholAdapter
import com.project.cocktails.viewmodel.NonAlcoholicViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class Non_AlcoholicFragment : Fragment() {

    private var viewModelAdapter: NonAlcoholAdapter? = null

    private val viewModel by viewModel<NonAlcoholicViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.non__alcoholic_fragment, container, false)
        val binding: NonAlcoholicFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.non__alcoholic_fragment, container, false
        )

        binding.nonalcviewmodel = viewModel
        viewModelAdapter = NonAlcoholAdapter(NonAlcClick {
            viewModel.displayPropertyDetails(it)
        })


        // navigate as well
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                Log.d("Data ", it.strDrink)
                this.findNavController().navigate(
                    Non_AlcoholicFragmentDirections.actionNonAlcToNonAlcDetails(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })


        binding.root.findViewById<RecyclerView>(R.id.non_alc_rv).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpObserverToGetData()
    }

    private fun setUpObserverToGetData() {
        viewModel.nonAlcResults.observe(viewLifecycleOwner, Observer<List<NonAlcohol>> { noAlc ->
            noAlc?.apply {
                viewModelAdapter?.results = noAlc
//                Toast.makeText(context, "Working", Toast.LENGTH_LONG).show()
                Log.i("Non Alcoholic results: ", noAlc.get(3).strDrink)
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
        fun newInstance(sectionNumber: Int): Non_AlcoholicFragment {
            return Non_AlcoholicFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
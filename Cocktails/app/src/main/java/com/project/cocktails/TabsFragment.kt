package com.project.cocktails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myapplication.SplashFragmentDirections

class TabsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val action1 = SplashFragmentDirections.showTabs()
//        findNavController().navigate(action1)
//        val action2 = TabsFragmentDirections.actionTabsFragmentToFirstFragment()
//        findNavController().navigate(action2)
    }

}
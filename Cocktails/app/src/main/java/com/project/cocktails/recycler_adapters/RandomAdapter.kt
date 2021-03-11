package com.project.cocktails.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.cocktails.R
import com.project.cocktails.databinding.RowRandomBinding
import com.project.cocktails.domain.Cocktail
import com.project.cocktails.domain.Random

class RandomAdapter(val callback: RandomClick): RecyclerView.Adapter<RandomViewHolder>() {

    var results: List<Random> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomViewHolder {
        val withDataBinding: RowRandomBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            RandomViewHolder.LAYOUT,
            parent,
            false
        )
        return RandomViewHolder(withDataBinding)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: RandomViewHolder, position: Int) {
        holder.viewDataBinding.also{
            it.results = results[position]
            it.resultsCallback = callback
        }
    }
}


class RandomViewHolder(val viewDataBinding: RowRandomBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.row_random
    }
}

class RandomClick(val block: (Random) -> Unit) {
    fun onClick(drink: Random) = block(drink)
}

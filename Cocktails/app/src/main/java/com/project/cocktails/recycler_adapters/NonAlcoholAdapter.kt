package com.project.cocktails.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.cocktails.R
import com.project.cocktails.databinding.RowCocktailBinding
import com.project.cocktails.databinding.RowNonAlcBinding
import com.project.cocktails.domain.Cocktail
import com.project.cocktails.domain.NonAlcohol

class NonAlcoholAdapter(val callback: NonAlcClick): RecyclerView.Adapter<NonAlcViewHolder>() {

    var results: List<NonAlcohol> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NonAlcViewHolder {
        val withDataBinding: RowNonAlcBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NonAlcViewHolder.LAYOUT,
            parent,
            false
        )
        return NonAlcViewHolder(withDataBinding)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: NonAlcViewHolder, position: Int) {
        holder.viewDataBinding.also{
            it.results = results[position]
            it.resultsCallback = callback
        }
    }

}

class NonAlcViewHolder(val viewDataBinding: RowNonAlcBinding):
    RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.row_non_alc
        }
}

class NonAlcClick(val block: (NonAlcohol) -> Unit) {
    fun onClick(drink: NonAlcohol) = block(drink)
}

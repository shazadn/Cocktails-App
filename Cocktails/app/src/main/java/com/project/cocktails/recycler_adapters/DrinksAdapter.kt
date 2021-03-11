package com.project.cocktails.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.project.cocktails.R
import com.project.cocktails.database.DatabaseCocktail
import com.project.cocktails.databinding.RowCocktailBinding
import com.project.cocktails.domain.Cocktail

class DrinksAdapter(val callback: CocktailClick) : RecyclerView.Adapter<DrinksViewHolder>() {

    var results: List<Cocktail> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {
        val withDataBinding: RowCocktailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DrinksViewHolder.LAYOUT,
            parent,
            false
        )
        return DrinksViewHolder(withDataBinding)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.results = results[position]
            //To handle onClick : In Databinding
            it.resultsCallback = callback
        }
    }
}


class DrinksViewHolder(val viewDataBinding: RowCocktailBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.row_cocktail
    }
}

class CocktailClick(val block: (Cocktail) -> Unit) {
    fun onClick(drink: Cocktail) = block(drink)
}


package com.badini.movie.ui.favoris

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badini.movie.data.model.db.Favoris
import com.badini.movie.databinding.FavorisItemBinding
import com.badini.movie.ui.base.BaseViewHolder

class FavorisAdapter: RecyclerView.Adapter<BaseViewHolder>(){

    private var favorisList: List<Favoris> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding: FavorisItemBinding = FavorisItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent, false);
        return FavorisViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (favorisList.size > 0) {
            favorisList.size
        } else {
            1
        }
    }

    fun update(items: List<Favoris>) {
        this.favorisList = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class FavorisViewHolder(binding: FavorisItemBinding) : BaseViewHolder(binding.getRoot()),
        FavorisItemViewModel.FavorisItemViewModelListener{

        private var mBinding: FavorisItemBinding? = null
        private var viewModel: FavorisItemViewModel? = null

        override fun onBind(position: Int) {
            if (!favorisList.isEmpty()){
                val favoris: Favoris = favorisList.get(position)
                viewModel = FavorisItemViewModel(favoris,this)
                mBinding?.favorisItemViewModel = viewModel
                mBinding?.executePendingBindings()
            }

        }

        init {
            mBinding = binding
        }

        override fun onItemClick(favoris: Favoris) {}

    }

}
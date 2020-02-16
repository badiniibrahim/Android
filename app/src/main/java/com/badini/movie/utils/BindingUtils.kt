package com.badini.movie.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.badini.movie.data.model.api.Movie
import com.badini.movie.data.model.db.Favoris
import com.badini.movie.ui.favoris.FavorisAdapter
import com.badini.movie.ui.main.MoviePageListAdapter
import com.bumptech.glide.Glide

class BindingUtils {

    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(
            imageView: ImageView,
            url: String?
        ) {
            val context = imageView.context
            val moviePosterURL = Constante.POSTER_BASE_URL + url
            Glide.with(context)
                .load(moviePosterURL)
                .into(imageView);
        }

        @JvmStatic
        @BindingAdapter("movie")
        fun bindRecyclerView(recyclerView: RecyclerView, data: PagedList<Movie>?){
            val adapter = recyclerView.adapter as MoviePageListAdapter
            adapter.submitList(data)
        }

        @JvmStatic
        @BindingAdapter("favoris")
        fun bindFavorisRecyclerView(recyclerView: RecyclerView, data: List<Favoris>){
            if (data.size > 0){
                val adapter = recyclerView.adapter as FavorisAdapter
                adapter.update(data)
            }

        }

    }
}
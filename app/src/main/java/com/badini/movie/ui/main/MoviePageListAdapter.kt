@file:JvmName("MoviePageListAdapter")

package com.badini.movie.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.badini.movie.R
import com.badini.movie.data.model.api.Movie
import com.badini.movie.ui.movieDetail.MovieDetailActivity
import com.badini.movie.utils.Constante
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_list_item.view.*
import kotlinx.android.synthetic.main.movie_list_item.view.movie_release_date
import kotlinx.android.synthetic.main.movie_list_item.view.movie_title

class MoviePageListAdapter: PagedListAdapter<Movie, MoviePageListAdapter.MovieViewHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }


    class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle = itemView.movie_title
        val movieReleaseDate = itemView.movie_release_date
        val img = itemView.img

        fun bindPost(movie: Movie){
            movieTitle.text = movie.title
            movieReleaseDate.text = movie.releaseDate
            val moviePosterURL = Constante.POSTER_BASE_URL + movie.posterPath
            Glide.with(itemView.context)
                .load(moviePosterURL)
                .into(img);

            img.setOnClickListener {
                Intent(itemView.context, MovieDetailActivity::class.java).also {
                    it.putExtra("id", movie.id)
                    itemView.context.startActivity(it)
                }
            }
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: MovieListItemBinding = MovieListItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent, false);
        return MoviePageListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.e("TAG", "movie : " +  position)

        //getItem(position)
    }*/

    /*override fun getItemCount(): Int {
        return if (movieList?.size!! > 0) {
            movieList!!.size
        } else {
            1
        }
    }*/
    /*fun update(items: List<Movie>) {
        this.movieList = items
        notifyDataSetChanged()
    }


    inner class MoviePageListViewHolder(binding: MovieListItemBinding): BaseViewHolder(binding.getRoot()),
        MovieItemViewModel.MovieItemViewModelListener{

        private val mBinding: MovieListItemBinding
        private var movieItemViewModel: MovieItemViewModel? =null

        override fun onBind(position: Int) {
            val movie: Movie = movieList!!.get(position)
            movieItemViewModel = MovieItemViewModel(movie, this)
            mBinding.itemViewModel = movieItemViewModel
            mBinding.executePendingBindings()
        }

        override fun onItemClick(movie: Movie?) {
            //TODO : clique
        }

        init {
            mBinding = binding
        }

    }*/



}
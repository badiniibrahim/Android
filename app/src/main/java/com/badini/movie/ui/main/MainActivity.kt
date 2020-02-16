package com.badini.movie.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.badini.movie.R
import com.badini.movie.data.model.db.Favoris
import com.badini.movie.data.network.NetWorkState
import com.badini.movie.databinding.ActivityMainBinding
import com.badini.movie.ui.favoris.FavorisActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), MainNavigator, KodeinAware {

    override val kodein by kodein()

    private val moviePageListAdapter = MoviePageListAdapter()


    var viewModel: MainViewModel? = null
    var activityMainBinding: ActivityMainBinding? = null
    private val mfactory : MainViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, mfactory).get(MainViewModel::class.java)
        activityMainBinding!!.viewModel = viewModel
        viewModel!!.setNavigator(this)
        subscribeToLiveData()
        initializeList()
    }

    private fun initializeList() {
        val resId: Int = R.anim.layout_animation_right_to_left
        val animation: LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(this, resId)
        movieRecyclerView.setLayoutAnimation(animation)
        movieRecyclerView.layoutManager = GridLayoutManager(this,3)
        movieRecyclerView.setHasFixedSize(true)
        movieRecyclerView.adapter = moviePageListAdapter
    }

    fun subscribeToLiveData(){
        viewModel!!.getPosts().observe(this, Observer {
            moviePageListAdapter.submitList(it)
        })

        viewModel!!.getNetworkState().observe(this, Observer {
            main_progress_bar.visibility = if (it === NetWorkState.LOADING) View.VISIBLE else View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_open_favoris -> {
                Intent(this, FavorisActivity::class.java).also {
                    startActivity(it)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

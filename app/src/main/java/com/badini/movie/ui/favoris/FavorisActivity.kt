package com.badini.movie.ui.favoris

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.badini.movie.R
import com.badini.movie.databinding.ActivityFavorisBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class FavorisActivity : AppCompatActivity(), FavorisNavigator, KodeinAware {

    override val kodein by kodein()

    private var viewModel: FavorisViewModel? = null
    private var favorisBinding: ActivityFavorisBinding? = null
    private val factory: FavorisViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(getString(R.string.favoris_title))
        favorisBinding = DataBindingUtil.setContentView(this, R.layout.activity_favoris)
        viewModel = ViewModelProviders.of(this, factory).get(FavorisViewModel::class.java)
        favorisBinding!!.favorisViewModel = viewModel
        viewModel!!.setNavigator(this)
        setupFavorisRecyclerView()
        subscribeToLiveData()
    }


    fun subscribeToLiveData(){
        viewModel!!.getFavorisListLiveData()!!.observe(this, Observer {
            viewModel!!.addFavorisItemsToList(it)
        })
    }

    fun setupFavorisRecyclerView(){
        favorisBinding?.favorisRecyclerView?.layoutManager = GridLayoutManager(this,2)
        favorisBinding?.favorisRecyclerView?.setHasFixedSize(true)
        favorisBinding?.favorisRecyclerView?.setItemAnimator(DefaultItemAnimator())
        favorisBinding?.favorisRecyclerView?.adapter = FavorisAdapter()
    }
}

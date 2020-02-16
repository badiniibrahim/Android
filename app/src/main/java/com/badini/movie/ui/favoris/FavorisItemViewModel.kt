package com.badini.movie.ui.favoris

import android.util.Log
import androidx.databinding.ObservableField
import com.badini.movie.data.model.db.Favoris

class FavorisItemViewModel(
    favoris: Favoris,
    favorisItemViewModelListener: FavorisItemViewModelListener

) {

    var title: ObservableField<String>? = null
    var releasedate: ObservableField<String>? = null
    var mListener: FavorisItemViewModelListener? = null
    var mFavoris: Favoris? = null
    var poster: ObservableField<String>? = null



    init {
        this.mFavoris = favoris
        this.mListener = favorisItemViewModelListener
        title = ObservableField(favoris.title)
        releasedate = ObservableField(favoris.releasedate)
        poster = ObservableField(favoris.imgurl)
    }


    interface FavorisItemViewModelListener {
        fun onItemClick(favoris: Favoris)
    }
}
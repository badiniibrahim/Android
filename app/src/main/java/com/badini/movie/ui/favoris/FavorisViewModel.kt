package com.badini.movie.ui.favoris

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.badini.movie.data.model.db.Favoris
import com.badini.movie.data.repositories.FavorisRepository
import com.badini.movie.ui.base.BaseViewModel
import com.badini.movie.utils.Coroutines

class FavorisViewModel(
    private val favorisRepository: FavorisRepository
): BaseViewModel<FavorisNavigator>() {

    val favorisObservableArrayList: ObservableList<Favoris> = ObservableArrayList()
    private var favorisListLiveData: MutableLiveData<List<Favoris>>? = null


    init {
        favorisListLiveData = MutableLiveData()
        fetchFavoris()
    }

    fun addFavorisItemsToList(favoris: List<Favoris>) {
        favorisObservableArrayList.clear()
        favorisObservableArrayList.addAll(favoris)
    }

    fun fetchFavoris(){
        Coroutines.main {
            try {
                favorisListLiveData?.value = favorisRepository.getAllFavoris()
            }catch (e: Exception){
                Log.e("TAG", "FavorisViewModel error fetch bdd : " + e)
            }
        }
    }

    fun getFavorisListLiveData(): MutableLiveData<List<Favoris>>? {
        return favorisListLiveData
    }

}
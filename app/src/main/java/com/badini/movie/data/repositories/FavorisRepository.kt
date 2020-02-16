package com.badini.movie.data.repositories

import com.badini.movie.data.local.db.AppDatabase
import com.badini.movie.data.model.db.Favoris

class FavorisRepository(
    private val db: AppDatabase
) {
    suspend fun getAllFavoris(): List<Favoris>{
        return db.getfavorisDao().loadAllFavoris()
    }
}
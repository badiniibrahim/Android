package com.badini.movie.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.badini.movie.data.model.db.Favoris

@Dao
interface FavorisDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(favoris: Favoris)

    @Query("SELECT * FROM Favoris")
    suspend fun loadAllFavoris(): List<Favoris>

}
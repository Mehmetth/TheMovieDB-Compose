package com.mehmetpetek.themoviedbcompose.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mehmetpetek.themoviedbcompose.data.local.model.TMDBEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TMDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<TMDBEntity>)

    @Query("Select * from movie where page=:page and sortBy=:sortBy")
    fun getMovies(page: Int, sortBy: String): Flow<List<TMDBEntity>>
}
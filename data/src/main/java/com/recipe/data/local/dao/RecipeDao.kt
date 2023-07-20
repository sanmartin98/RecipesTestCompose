package com.recipe.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.recipe.data.local.model.RecipeEntity

@Dao
abstract class RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(recipes: List<RecipeEntity>)

    @Query("SELECT * FROM recipes")
    abstract fun getRecipes(): List<RecipeEntity>
}
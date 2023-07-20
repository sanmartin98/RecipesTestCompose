package com.recipe.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.recipe.data.local.converter.Converter
import com.recipe.data.local.dao.RecipeDao
import com.recipe.data.local.model.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = RecipeRoomDataBase.VERSION
)
@TypeConverters(Converter::class)
abstract class RecipeRoomDataBase: RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao

    companion object {
        const val VERSION = 1
    }
}
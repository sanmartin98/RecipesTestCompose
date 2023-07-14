package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.converter.Converter
import com.example.data.local.dao.RecipeDao
import com.example.data.local.model.RecipeEntity

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
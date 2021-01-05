package com.funcode.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.funcode.core.data.source.local.entity.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
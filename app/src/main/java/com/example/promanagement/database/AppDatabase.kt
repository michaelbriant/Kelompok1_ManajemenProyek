package com.example.promanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.promanagement.database.TeamMember
import com.example.promanagement.database.TeamMemberDao


@Database(entities = [TeamMember::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun teamMemberDao(): TeamMemberDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "project_management_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

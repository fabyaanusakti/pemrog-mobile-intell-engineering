package org.group5.IntelliEngineer.data.database

import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.group5.IntelliEngineer.data.auth.dao.UserDao
import org.group5.IntelliEngineer.data.auth.entity.User


@Database(entities = [User::class], version = 2) // BUMP version to 2
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        // MIGRATION from version 1 to 2: Add email column
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE user ADD COLUMN email TEXT")
            }
        }

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                )
                    .addMigrations(MIGRATION_1_2) // ✅ Add migration here
                    //.fallbackToDestructiveMigration() // use ONLY if you want to wipe data on every schema change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

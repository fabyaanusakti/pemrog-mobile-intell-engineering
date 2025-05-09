package org.group5.IntelliEngineer.data.database

import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.group5.IntelliEngineer.data.Project
import org.group5.IntelliEngineer.data.ProjectDao
import org.group5.IntelliEngineer.data.auth.dao.UserDao
import org.group5.IntelliEngineer.data.auth.entity.User


@Database(entities = [User::class, Project::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun projectDao(): ProjectDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        // uncomment this for migration
//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(db: SupportSQLiteDatabase) {
//                db.execSQL("ALTER TABLE user ADD COLUMN email TEXT") // add the new sql query here
//            }
//        }

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                )
                    //.addMigrations(MIGRATION_1_2) // uncomment this for migration
                    .fallbackToDestructiveMigration(true) // use ONLY if you want to wipe data on every schema change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

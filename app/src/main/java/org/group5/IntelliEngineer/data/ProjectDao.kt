package org.group5.IntelliEngineer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete


@Dao
interface ProjectDao {

    @Insert
    suspend fun insert(project: Project)

    @Query("SELECT * FROM Project WHERE userId = :userId")
    suspend fun getProjectsForUser(userId: Int): List<Project>

    @Update
    suspend fun update(project: Project)

    @Delete
    suspend fun delete(project: Project)
}
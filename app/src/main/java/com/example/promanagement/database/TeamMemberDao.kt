package com.example.promanagement.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TeamMemberDao {

    @Insert
    suspend fun insert(teamMember: TeamMember)

    @Query("SELECT * FROM team_members")
    suspend fun getAll(): List<TeamMember>
}

package com.example.promanagement.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_members")
data class TeamMember(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val tanggalLahir: String,
    val alamat: String,
    val jenisKelamin: String,
    val keahlian: String,
    val peran: String,
    val fotoUri: String? = null // Optional field for storing the photo URI
)
s
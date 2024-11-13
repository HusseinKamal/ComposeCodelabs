package com.hussein.composecodelab.compose.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users") // Specify the table name here
data class User(@PrimaryKey val id: Int, val name: String)

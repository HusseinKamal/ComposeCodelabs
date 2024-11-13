package com.hussein.composecodelab.compose.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // Replace if a user with the same ID already exists
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)


    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM users") // Assumes your table name is "users"
    suspend fun deleteAllUsers()


    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): User? // Returns null if not found


    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>> // Use Flow for observing changes



    @Query("SELECT * FROM users WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchUsersByName(searchQuery: String): Flow<List<User>>
}
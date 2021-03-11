package com.project.cocktails.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RandomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(drinks: List<DatabaseRandom>)

    @Query("SELECT * FROM  DatabaseRandom")
    fun getLocalDBDrinks(): LiveData<List<DatabaseRandom>>
}

@Database(entities = [DatabaseRandom::class], version = 5, exportSchema = false)
abstract class RandomDatabase : RoomDatabase() {
    abstract val drinkDao: RandomDao
}
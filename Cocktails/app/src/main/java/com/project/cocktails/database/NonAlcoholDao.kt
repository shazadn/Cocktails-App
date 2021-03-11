package com.project.cocktails.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NonAlcoholDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(drinks: List<DatabaseNonAlc>)

    @Query("SELECT * FROM  DatabaseNonAlc")
    fun getLocalDBDrinks(): LiveData<List<DatabaseNonAlc>>

}

@Database(entities = [DatabaseNonAlc::class], version = 2, exportSchema = false)
abstract class NonAlcoholDatabase : RoomDatabase() {
    abstract val drinkDao: NonAlcoholDao
}
package com.project.cocktails.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(drinks: List<DatabaseCocktail>)

    @Query("SELECT * FROM  DatabaseCocktail")
    fun getLocalDBDrinks(): LiveData<List<DatabaseCocktail>>
}

@Database(entities = [DatabaseCocktail::class], version = 5, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {
    abstract val drinkDao: CocktailDao
}

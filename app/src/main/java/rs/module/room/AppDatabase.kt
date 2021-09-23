package rs.module.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * This is a database class that defines database configuration
 * and serves as the app's main access point to the persisted data
 * It must satisfy the following conditions:
 *  - It must be annotated with @Database annotation with entities array that lists all of the
 *  data entities associated with the database
 *  - It must be an abstract class that extends RoomDatabase
 *  - For each DAO class associated with the database, the database must define an abstract
 *  zero argument method that returns the instance of the DAO class
 */

/**
 * @Database annotation with list of entities associated with this database
 */
@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Abstract zero argument function
     * that returns the DAO associated with this database
     */
    abstract fun getUserDAO():UserDao
}
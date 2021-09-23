package rs.module.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * This DAO provides methods for rest of the app to interact with the user table
 *
 * A DAO should always be annotated with a @Dao
 * A DAO can either be an interface or an abstract class
 * A DAO doesn't have any properties or fields
 *
 * DAO methods can be defined in two ways:
 * - With SQL query for complex operations
 * - With convenience method eg. @Insert, @Delete etc for simple operations
 *
 * CONVENIENCE METHOD
 * @Insert - inserts data in the database
 * Eg.
 * @Insert
 * fun insertUser(vararg users:User)
 *
 * onConflict method can be used to handle duplicate data
 * Eg.
 * @Insert( onConflict = OnConflictStrategy.REPLACE)
 * fun insertUsers(vararg users:User)
 *
 * The @Insert method returns a long, or an array of long, with the id of the newly inserted data
 * The @Update method optionally returns an int indicating the number of rows updated
 * The @Delete method optionally returns an int indication the number of rows deleted
 *
 * These methods compare the primary key of the entity to be updated or deleted with that of the one in
 * the table - no match means no changes
 *
 *
 * QUERY METHOD
 *
 * It allows SQL queries to be written and expose them as DAO methods. These query methods are validated
 * during the compile time
 *
 */

@Dao
interface UserDao {

    /**
     * Query methods
     */
    @Query("SELECT * FROM user")
    fun getAll():List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds:IntArray):List<User>

    @Query("SELECT * FROM user WHERE firstName LIKE :first AND lastName LIKE :last LIMIT 1")
    fun findByName(first: String, last:String):User

    /**
     * Convenience methods
     */
    @Insert
    fun insertAll(vararg  users:User)

    @Delete
    fun delete(user:User)

}
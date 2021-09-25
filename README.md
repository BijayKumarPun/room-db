# room-db
Saving data locally in Android using [Room](https://developer.android.com/training/data-storage/room#kts)

**Main components of Room:**  


`Database class`  
Holds the database and acts as the main access point


`DAOs - Data Access Objects`  
Provides method for performing operation on the database


`Data Entities`  
Represents tables in the database

> The `Database` provides `DAO` which in turn provides method to make changes in the `data entities`

**Dependency**
```
    //dependencies for Room

    implementation 'androidx.room:room-runtime:2.3.0'
    annotationProcessor 'androidx.room:room-compiler:2.3.0'
    // To use Kotlin annotation processing tool (kapt)
    kapt 'androidx.room:room-compiler:2.3.0'
    // To use Kotlin Symbolic Processing (KSP)
    kapt 'androidx.room:room-compiler:2.3.0'
```

While setting up dependencies, make sure to use the `kotlin-kapt` plugin as well.

```
plugins {
    id 'kotlin-kapt'
}
```


**Defining Room entities**  

```

/**
 * To use a different name the @Entity annotation can be used
 * Eg. @Entity(tableName = "users")
 * ------------------------------------------------------
 * To use a different column name, by default field name is the column name, then
 * @ColumnInfo annotation can be used
 * Eg. @ColumnInfo(name = "first_name")
 * ------------------------------------------------------
 * Multiple primary keys can be used, called composite primary keys, which is when
 * a single row has to be identified by multiple unique columns, then
 * primaryKeys property of @Entity annotation can be used
 * Eg. @Entity(primaryKeys = arrayOf("firstName","lastName"))
 * Here, 'firstName' and 'lastName' will be the composite keys
 * ------------------------------------------------------
 * To ignore a field, use @Ignore annotation
 * Eg. @Ignore val picture:Bitmap
 * ------------------------------------------------------
 * Also, to ignore field from parent entity, use ignoredColumns of @Entity attribute
 * Eg.
 * open class User {
 * var picture:Bitmap? = null }
 * ------------------------------------------------------
 * @Entity( ignoredColumns = arrayOf("picture))
 * data class ChildUser(
 * @PrimaryKey val id:Int)
 * : User()
 * ------------------------------------------------------
 * To support full text search, use @Fts3 or @Fts4 attribute
 * @Fts3 is backward compatible and is compatible with low disk space
 * Note that FTS enabled primary key should always be of Integer type with column name rowid
 * Eg.
 * @Fts4
 * @Entity(tableName = "users")
 * data class User(
 * @PrimaryKey @ColumnInfo(name = "rowid") val id:Int
 * )
 *  ------------------------------------------------------
 */
 ```
 
 
 ```
 @Entity
data class User(
   @PrimaryKey val uid:Int,
   val firstName:String?,
   @ColumnInfo(name = "last_name") val lastName:String?,
   @Ignore val picture: Bitmap?
)

```

**Defining Room DAO**
```
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

    @Query("SELECT * FROM user WHERE firstName LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last:String):User

    /**
     * Convenience methods
     */
    @Insert
    fun insertAll(vararg  users:User)

    @Delete
    fun delete(user:User)

}
```

**Defining Room Database**
```

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
```

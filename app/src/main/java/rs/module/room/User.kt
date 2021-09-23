package rs.module.room

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

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
@Entity
data class User(
   @PrimaryKey val uid:Int,
   val firstName:String?,
   @ColumnInfo(name = "last_name") val lastName:String?,
   @Ignore val picture: Bitmap?
)

package rs.module.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Note that the Room Database class acts as the main entry point for
         * rest of the app.
         * The database class provides a DAO object that has methods to interact
         * with the database tables/entities
         *
         */

        /**
         * Create instance of the database
         */

        val db = Room.databaseBuilder(this,AppDatabase::class.java,"my-database")
            .build()

        /**
         * Get DAO object from the database instance
         */
        val userDao:UserDao = db.getUserDAO()

        /**
         * Use the DAO methods to access the data
         */
        val users:List<User> = userDao.getAll()

    }
}
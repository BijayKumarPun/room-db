package rs.module.room

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    lateinit var userDao: UserDao
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
            .allowMainThreadQueries()
            .build()

        /**
         * Get DAO object from the database instance
         */
      userDao = db.getUserDAO()

        /**
         * Use the DAO methods to access the data
         */
        val users:List<User> = userDao.getAll()


        //Init UI
        val firstName:EditText = findViewById(R.id.et_firstname)
        val lastName:EditText = findViewById(R.id.et_lastname)
        val buttonInsert:Button = findViewById(R.id.btn_insert)

        //insert
        buttonInsert.setOnClickListener{
          if  (firstName.text.toString().isNotEmpty() && lastName.text.toString().isNotEmpty()){
              insertNewUser(firstName.text.toString(),lastName.text.toString())
          }
        }
    }

    private fun insertNewUser(firstName:String, lastName:String) {
        val user:User = User(
            System.currentTimeMillis().toInt(), firstName = firstName,lastName = lastName,
            pictureUrl = "https://random.com/image1"
        )
        val id = userDao.insertAll(user)
        Toast.makeText(this@MainActivity, "Inserted with id $id",Toast.LENGTH_SHORT).show()

    }
}
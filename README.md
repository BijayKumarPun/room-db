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


While setting up dependencies, make sure to use the `kotlin-kapt` plugin as well.

```
plugins {
    id 'kotlin-kapt'
}
```


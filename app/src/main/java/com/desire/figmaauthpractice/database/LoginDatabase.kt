package com.desire.figmaauthpractice.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.desire.figmaauthpractice.App


@Database(entities = [LoginDataClass::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun loginDao():LoginDao

    companion object{
        @Volatile
        private var INSTANCE:LoginDatabase ?=null;

        fun getDatabase() : LoginDatabase{
            val temp = INSTANCE
            if(temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    App.applicationContext(),
                    LoginDatabase::class.java,
                    "login_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
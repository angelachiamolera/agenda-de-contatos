package br.com.angelachiamolera.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [Contact::class], version = 1)
abstract class ContactRoomDatabase : RoomDatabase() {

    abstract fun getContactDAO(): ContactRoomDAO

    companion object {

        @Volatile
        private var INSTANCE: ContactRoomDatabase? = null

        internal fun getDatabase(context: Context): ContactRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(ContactRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ContactRoomDatabase::class.java, "contact_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
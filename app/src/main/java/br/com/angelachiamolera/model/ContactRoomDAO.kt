package br.com.angelachiamolera.model

import androidx.room.*

@Dao
public interface ContactRoomDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Query("DELETE FROM contact_table")
    fun deleteAll()

    @Query("SELECT * from contact_table")
    fun getAllContacts(): List<Contact>

    @Update
    fun updateContatcs(vararg contact: Contact)

    @Delete
    fun deleteContatcs(vararg contact: Contact)
}
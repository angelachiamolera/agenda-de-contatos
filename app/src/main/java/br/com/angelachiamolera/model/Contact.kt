package br.com.angelachiamolera.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contact_table")
data class Contact( @PrimaryKey(autoGenerate = true)
                    val id: Int? = null,
    var nome: String, var cellphone: String, var email: String
) : Serializable
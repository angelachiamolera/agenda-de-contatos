package br.com.angelachiamolera.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import br.com.angelachiamolera.R
import br.com.angelachiamolera.model.Contact
import br.com.angelachiamolera.model.ContactDAO

class EditContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_contact)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        var titleToolbar = findViewById<TextView>(R.id.tv_title_toolbar)
        titleToolbar.setText("EDITAR O CONTATO")

        val contact = intent.extras.getSerializable("Contato") as? Contact
        val position = intent.extras.getInt("Position")

        val nameContact = findViewById<TextView>(R.id.et_name_contact)
        val cellphoneContact = findViewById<TextView>(R.id.et_cellphone_contact)
        val emailContact = findViewById<TextView>(R.id.et_email_contact)

        contact?.let {
            nameContact.text = contact.nome
            cellphoneContact.text = contact.cellphone
            emailContact.text = contact.email

            findViewById<Button>(R.id.button_edit_contact).setOnClickListener {

                contact.nome = nameContact.text.toString()
                contact.cellphone = cellphoneContact.text.toString()
                contact.email = emailContact.text.toString()

                if(ContactDAO.editContact(contact, position)) {
                    Toast.makeText(this, "Seu contato foi editado com sucesso.", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }
}

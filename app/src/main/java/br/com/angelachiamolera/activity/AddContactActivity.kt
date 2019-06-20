package br.com.angelachiamolera.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import br.com.angelachiamolera.R
import br.com.angelachiamolera.model.Contact
import br.com.angelachiamolera.model.ContactDAO

class AddContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        var titleToolbar = findViewById<TextView>(R.id.tv_title_toolbar)
        titleToolbar.setText("ADICIONAR CONTATO")

        var nameContact = findViewById<TextView>(R.id.et_name_contact)
        var cellphoneContact = findViewById<TextView>(R.id.et_cellphone_contact)
        var emailContact = findViewById<TextView>(R.id.et_email_contact)


        val btnAddContact = findViewById<Button>(R.id.button_add_contact)

        btnAddContact.setOnClickListener(View.OnClickListener {

            var name = nameContact.text.toString()
            var cellphone = cellphoneContact.text.toString()
            var email = emailContact.text.toString()

            var contact = Contact(name, email = email, cellphone = cellphone)

            ContactDAO.getInstance()
            if(ContactDAO.saveContact(contact)) {
                Toast.makeText(this, "Seu contato " + name + " foi adicionado com sucesso.", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}

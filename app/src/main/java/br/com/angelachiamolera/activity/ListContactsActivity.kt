package br.com.angelachiamolera.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import br.com.angelachiamolera.R
import br.com.angelachiamolera.model.Contact
import br.com.angelachiamolera.model.ContactDAO
import br.com.angelachiamolera.view.ContactsAdapter

class ListContactsActivity : AppCompatActivity() {

    private lateinit var listView : ListView
    var listContacts : List<Contact>? = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_contacts)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        var titleToolbar = findViewById<TextView>(R.id.tv_title_toolbar)
        titleToolbar.setText("LISTA DE CONTATOS")

        listView = findViewById<ListView>(R.id.list_contacts)

        callLoadListContact()

        listView.setOnItemClickListener { _, _, position, _ ->
            val contact = listContacts!![position]
            val intent = Intent(this, DetailContactActivity::class.java)
            intent.putExtra("Contato", contact)
            intent.putExtra("Position", listContacts!![position])
            startActivity(intent)
        }
    }

    private fun callLoadListContact() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_contacts)
        ContactDAO.getListContactFromRoom(object : ContactDAO.onResult<List<Contact>> {
            override fun onSuccess(result: List<Contact>) {
                listContacts = result
                progressBar.visibility = View.GONE
                setAdapter(result)
            }
        })
    }

    private fun setAdapter(listContacts: List<Contact>) {
        val adapter = ContactsAdapter(this, listContacts)
        listView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        callLoadListContact()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}

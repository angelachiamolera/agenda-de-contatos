package br.com.angelachiamolera.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import br.com.angelachiamolera.R
import br.com.angelachiamolera.model.ContactDAO
import br.com.angelachiamolera.view.ContactsAdapter

class ListContactsActivity : AppCompatActivity() {

    private lateinit var listView : ListView

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
        var listContacts = ContactDAO.getList()


        listContacts?.let {
            val adapter = ContactsAdapter(this, listContacts)
            listView.adapter = adapter
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            // 1
            val contact = listContacts?.get(position)

            val intent = Intent(this, DetailContactActivity::class.java)
            intent.putExtra("Contato", contact)
            intent.putExtra("Position", listContacts!![position])
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        var listContacts = ContactDAO.getList()

        listContacts?.let {
            val adapter = ContactsAdapter(this, listContacts)
            listView.adapter = adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}

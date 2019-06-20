package br.com.angelachiamolera.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import br.com.angelachiamolera.R
import br.com.angelachiamolera.model.Contact
import br.com.angelachiamolera.model.ContactDAO

class DetailContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_contact)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        var titleToolbar = findViewById<TextView>(R.id.tv_title_toolbar)
        titleToolbar.setText("DETALHE DO CONTATO")

        val contact = intent.extras.getSerializable("Contato") as? Contact
        val position = intent.extras.getInt("Position")

        val tvName = findViewById<TextView>(R.id.tv_name_contact)
        val tvCellphone = findViewById<TextView>(R.id.tv_cellphone_contact)
        val tvEmail = findViewById<TextView>(R.id.tv_email_contact)

        val btnDelete = findViewById<TextView>(R.id.button_delete_contact)

        btnDelete.setOnClickListener(View.OnClickListener {
            if (ContactDAO.delete(position)) {
                Toast.makeText(this, "Seu contato foi deletado com sucesso.", Toast.LENGTH_LONG).show()
                finish()
            }
        })

        val btnEdit = findViewById<TextView>(R.id.button_edit_contact)

        btnEdit.setOnClickListener {
            contact?.let {
                finish()
                val intent = Intent(this, EditContactActivity::class.java)
                intent.putExtra("Contato", it)
                intent.putExtra("Position", position)
                startActivity(intent)
            }
        }

        contact?.let {
            tvName.text = contact.nome
            tvCellphone.text = contact.cellphone
            tvEmail.text = contact.email
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}

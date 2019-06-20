package br.com.angelachiamolera.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import br.com.angelachiamolera.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnAddContact = findViewById<Button>(R.id.button_add_contact)

        btnAddContact.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        })

        val btnListContact = findViewById<Button>(R.id.button_list_contact)

        btnListContact.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListContactsActivity::class.java)
            startActivity(intent)
        })
    }


}

package br.com.angelachiamolera.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.angelachiamolera.R
import br.com.angelachiamolera.model.Contact
import android.view.LayoutInflater



class ContactsAdapter(var context: Context, var listContact: List<Contact>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater
            .from(context) as LayoutInflater

        val convertView = inflater.inflate(R.layout.list_item_contact, parent, false)

        var tvName = convertView.findViewById<TextView>(R.id.tv_name_contact)
        var tvCellphone = convertView.findViewById<TextView>(R.id.tv_cellphone_contact)

        tvName.text = getItem(position).nome
        tvCellphone.text = getItem(position).cellphone

        return convertView
    }

    override fun getItem(position: Int): Contact {
        return listContact[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listContact.size
    }
}
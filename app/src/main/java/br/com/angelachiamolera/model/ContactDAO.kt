package br.com.angelachiamolera.model

class ContactDAO {

    companion object {
        var listContacts : MutableList<Contact>? = null

        fun getInstance() : MutableList<Contact> {

            if(listContacts == null) {
                listContacts = mutableListOf()
            }

            return listContacts!!
        }

        fun saveContact(contact: Contact) : Boolean{
            return listContacts!!.add(contact)
        }

        fun getContactByPosition(index: Int) : Contact {
            return listContacts!!.get(index)
        }
    }
}
package br.com.angelachiamolera.model

class ContactDAO {

    companion object {
        private var listContacts : MutableList<Contact>? = null

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

        fun getList() : MutableList<Contact>? {
            return listContacts
        }

        fun delete(position: Int) : Boolean {
            listContacts!!.removeAt(position)
            return true
        }

        fun editContact(contact: Contact, position: Int): Boolean {
            if(delete(position)) {
                listContacts!!.add(position, contact)
            }
            return true
        }
    }
}
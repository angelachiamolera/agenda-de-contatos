package br.com.angelachiamolera.model

import android.content.Context
import android.os.AsyncTask



class ContactDAO {

    interface onResult<T> {
        fun onSuccess(result: T)
    }

    companion object {
        private var db: ContactRoomDatabase? = null
        private var listContacts: MutableList<Contact>? = null

        fun getInstanceRoom(context: Context): ContactRoomDatabase {

            if (db == null) {
                db = ContactRoomDatabase.getDatabase(context = context)
            }

            return db!!
        }

        fun saveContactRoom(contact: Contact): Boolean {
            val contactRoomDao = db!!.getContactDAO()
            return saveOutOfMainThread(contactRoomDao, contact)
        }

        fun saveOutOfMainThread(
            contactDAO: ContactRoomDAO,
            contact: Contact
        ): Boolean {
            AsyncTask.execute {
                contactDAO.insert(contact)
            }
            return true
        }

        fun getInstance(): MutableList<Contact> {

            if (listContacts == null) {
                listContacts = mutableListOf()
            }

            return listContacts!!
        }

        fun saveContact(contact: Contact): Boolean {
            return listContacts!!.add(contact)
        }

        fun getContactByPosition(index: Int): Contact {
            return listContacts!!.get(index)
        }

        fun getListContactFromRoom(onResult: onResult<List<Contact>>) {
            val contactRoomDao = db!!.getContactDAO()
            return getListContactOutMainThread(contactRoomDao, onResult)
        }

        private fun getListContactOutMainThread(
            contactRoomDao: ContactRoomDAO,
            onResult: onResult<List<Contact>>
        ) {
            GetAllContactsAsyncTask(contactRoomDao, onResult).execute()
        }

        fun getList(): MutableList<Contact>? {
            return listContacts
        }

        fun delete(position: Int): Boolean {
            listContacts!!.removeAt(position)
            return true
        }

        fun editContact(contact: Contact, position: Int): Boolean {
            if (delete(position)) {
                listContacts!!.add(position, contact)
            }
            return true
        }

        fun editContactFromRoom(
            contact: Contact,
            onResult: onResult<Boolean>
        ) {
            val contactRoomDao = db!!.getContactDAO()
            return editOutOfMainThread(contactRoomDao, contact, onResult)
        }

        private fun editOutOfMainThread(contactRoomDao: ContactRoomDAO, contact: Contact, onResult: onResult<Boolean>){
            EditContactsAsyncTask(contactRoomDao, contact , onResult).execute()
        }

        fun deleteContactFromRoom(contact: Contact, onResult: onResult<Boolean>) {
            val contactRoomDao = db!!.getContactDAO()
            return deleteOutOfMainThread(contactRoomDao, contact, onResult)
        }

        private fun deleteOutOfMainThread(
            contactRoomDao: ContactRoomDAO,
            contact: Contact,
            onResult: onResult<Boolean>
        ) {
            DeleteContactsAsyncTask(contactRoomDao, contact , onResult).execute()
        }

        //region AsyncTask
        //region AsyncTask GetAllContactsAsyncTask
        private class GetAllContactsAsyncTask internal constructor(
            private val asyncContactDao: ContactRoomDAO,
            private val asyncDelegate: onResult<List<Contact>>?
        ) : AsyncTask<Void, Void, List<Contact>>() {
            override fun doInBackground(vararg args: Void): List<Contact>? {
                return asyncContactDao.getAllContacts()
            }

            override fun onPostExecute(result: List<Contact>?) {
                asyncDelegate?.let {
                    result?.let {
                        asyncDelegate.onSuccess(result)
                    }
                }
            }
        }
        //endregion AsyncTask GetAllContactsAsyncTask
        //region AsyncTask DeleteContactsAsyncTask
        private class DeleteContactsAsyncTask internal constructor(
            private val asyncContactDao: ContactRoomDAO,
            private val contact: Contact,
            private val asyncDelegate: onResult<Boolean>?
        ) : AsyncTask<Void, Void, Boolean>() {
            override fun doInBackground(vararg params: Void?): Boolean {
                asyncContactDao.deleteContatcs(contact)
                return true
            }

            override fun onPostExecute(result: Boolean?) {
                asyncDelegate?.let {
                    result?.let {
                        asyncDelegate.onSuccess(result)
                    }
                }
            }
        }
        //endregion AsyncTask DeleteContactsAsyncTask
        //region AsyncTask EditContactsAsyncTask
        private class EditContactsAsyncTask internal constructor(
            private val asyncContactDao: ContactRoomDAO,
            private val contact: Contact,
            private val asyncDelegate: onResult<Boolean>?
        ) : AsyncTask<Void, Void, Boolean>() {
            override fun doInBackground(vararg params: Void?): Boolean {
                asyncContactDao.updateContatcs(contact)
                return true
            }

            override fun onPostExecute(result: Boolean?) {
                asyncDelegate?.let {
                    result?.let {
                        asyncDelegate.onSuccess(result)
                    }
                }
            }
        }
        //endregion AsyncTask EditContactsAsyncTask
        //endregion AsyncTask
    }
}




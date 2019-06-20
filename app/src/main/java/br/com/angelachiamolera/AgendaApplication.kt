package br.com.angelachiamolera

import android.app.Application
import br.com.angelachiamolera.model.ContactDAO
import br.com.angelachiamolera.model.ContactRoomDatabase

class AgendaApplication : Application() {
    companion object {
        var db: ContactRoomDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        ContactDAO.getInstanceRoom(this)
    }
}
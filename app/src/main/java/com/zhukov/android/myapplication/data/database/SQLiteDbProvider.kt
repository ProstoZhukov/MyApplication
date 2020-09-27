package com.zhukov.android.myapplication.data.database

import com.zhukov.android.myapplication.data.database.IDataBaseProvider
import com.zhukov.android.myapplication.data.database.MyContactSQLite.IMyContactSQlite
import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class SQLiteDbProvider(iMyContactSQlite: IMyContactSQlite) :
    IDataBaseProvider {

    private var mDataBase: IMyContactSQlite = iMyContactSQlite


    override fun deleteContact(contactId: UUID): Completable {
        return Completable.fromAction { mDataBase.deleteContact(contactId) }
    }

    override fun addNewContact(): Single<UUID> {
        return Single.fromCallable { mDataBase.addContact() }
    }

    override fun loadContactList(): Single<List<ContactModel>> {
        return Single.fromCallable { mDataBase.getContacts() }
    }

    override fun loadContact(contactId: UUID): Single<ContactModel> {
        return Single.fromCallable { mDataBase.getContact(contactId) }
    }

    override fun updateContact(contactModel: ContactModel): Completable {
        return  Completable.fromAction { mDataBase.updateContact(contactModel) }
    }
}
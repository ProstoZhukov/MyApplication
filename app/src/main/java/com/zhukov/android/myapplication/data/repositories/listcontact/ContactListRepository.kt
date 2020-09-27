package com.zhukov.android.myapplication.data.repositories.listcontact

import com.zhukov.android.myapplication.data.database.IDataBaseProvider
import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class ContactListRepository(dataBaseProvider: IDataBaseProvider) : IContactListRepository {

    private val mDataBaseProvider: IDataBaseProvider = dataBaseProvider

    override fun addNewContact(): Single<UUID> {
        return mDataBaseProvider.addNewContact()
    }

    override fun deleteContact(contactId: UUID): Completable {
        return mDataBaseProvider.deleteContact(contactId)
    }

    override fun loadContactList(): Single<List<ContactModel>> {
        return mDataBaseProvider.loadContactList()
    }

}
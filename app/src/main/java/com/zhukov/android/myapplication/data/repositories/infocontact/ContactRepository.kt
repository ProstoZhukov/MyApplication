package com.zhukov.android.myapplication.data.repositories.infocontact

import com.zhukov.android.myapplication.data.database.IDataBaseProvider
import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Single
import java.util.*

class ContactRepository(dataBaseProvider: IDataBaseProvider) :
    IContactRepository {

    private val mDataBaseProvider: IDataBaseProvider = dataBaseProvider

    override fun loadContact(contactId: UUID): Single<ContactModel> {
        return mDataBaseProvider.loadContact(contactId)
    }

}
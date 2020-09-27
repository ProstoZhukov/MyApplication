package com.zhukov.android.myapplication.data.repositories.editcontact

import com.zhukov.android.myapplication.data.database.IDataBaseProvider
import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class EditContactRepository(iDataBaseProvider: IDataBaseProvider) :
    IEditContactRepository {
    private val mDataBaseProvider: IDataBaseProvider = iDataBaseProvider

    override fun loadContact(contactId: UUID): Single<ContactModel> {
        return mDataBaseProvider.loadContact(contactId)
    }

    override fun updateContact(contactModel: ContactModel): Completable {
        return mDataBaseProvider.updateContact(contactModel)
    }

}
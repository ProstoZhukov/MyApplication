package com.zhukov.android.myapplication.busines.editcontact

import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface IEditContactInteractor {
    fun loadContact(contactId: UUID): Single<ContactModel>

    fun updateContact(contactModel: ContactModel): Completable
}
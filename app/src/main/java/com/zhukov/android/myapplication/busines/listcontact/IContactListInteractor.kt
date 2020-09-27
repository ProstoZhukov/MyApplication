package com.zhukov.android.myapplication.busines.listcontact

import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface IContactListInteractor {
    fun deleteContact(contactId: UUID): Completable

    fun addNewContact(): Single<UUID>

    fun loadContactList(): Single<List<ContactModel>>
}
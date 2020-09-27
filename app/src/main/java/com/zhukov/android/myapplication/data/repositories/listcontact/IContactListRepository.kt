package com.zhukov.android.myapplication.data.repositories.listcontact

import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface IContactListRepository {
    fun deleteContact(contactId: UUID): Completable
    fun addNewContact(): Single<UUID>
    fun loadContactList(): Single<List<ContactModel>>
}
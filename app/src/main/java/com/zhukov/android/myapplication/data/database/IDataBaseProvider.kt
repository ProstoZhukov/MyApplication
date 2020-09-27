package com.zhukov.android.myapplication.data.database

import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface IDataBaseProvider {

    fun deleteContact(contactId: UUID): Completable

    fun addNewContact(): Single<UUID>

    fun loadContactList(): Single<List<ContactModel>>

    fun loadContact(contactId: UUID): Single<ContactModel>

    fun updateContact(contactModel: ContactModel): Completable
}
package com.zhukov.android.myapplication.data.database.MyContactSQLite

import com.zhukov.android.myapplication.data.database.model.ContactModel
import java.util.*

interface IMyContactSQlite {

    fun deleteContact(contactId: UUID)

    fun addContact(): UUID

    fun getContacts(): List<ContactModel>

    fun getContact(contactId: UUID): ContactModel?

    fun updateContact(contactModel: ContactModel)
}
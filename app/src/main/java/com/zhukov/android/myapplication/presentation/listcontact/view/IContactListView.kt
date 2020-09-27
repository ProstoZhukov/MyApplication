package com.zhukov.android.myapplication.presentation.listcontact.view

import com.zhukov.android.myapplication.data.database.model.ContactModel
import java.util.*

interface IContactListView {

    fun openContactScreen(contactId: UUID)

    fun openEditContactScreen(contactId: UUID)

    fun updateContactList(contactModelList: List<ContactModel>)

    fun updateContactListSecondName(modelList: List<ContactModel>): List<ContactModel>

    fun updateContactListFirstName(modelList: List<ContactModel>): List<ContactModel>
}
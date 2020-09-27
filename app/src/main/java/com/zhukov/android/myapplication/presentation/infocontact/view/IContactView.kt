package com.zhukov.android.myapplication.presentation.infocontact.view

import com.zhukov.android.myapplication.data.database.model.ContactModel

interface IContactView {

    fun openEditContactScreen()

    fun goToListContacts()

    fun updateUI(contactsModel: ContactModel)
}
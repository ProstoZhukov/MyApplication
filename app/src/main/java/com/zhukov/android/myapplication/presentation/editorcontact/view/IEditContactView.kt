package com.zhukov.android.myapplication.presentation.editorcontact.view

import com.zhukov.android.myapplication.data.database.model.ContactModel

interface IEditContactView {

    fun updateUI(contactsModel: ContactModel)

    fun updateContact()

    fun updatePhoto()

    fun itemAddNumberClicked()

    fun itemAddSocialClicked()

}
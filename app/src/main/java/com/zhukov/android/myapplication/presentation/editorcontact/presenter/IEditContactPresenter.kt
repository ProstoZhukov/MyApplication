package com.zhukov.android.myapplication.presentation.editorcontact.presenter

import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.presentation.editorcontact.view.IEditContactView
import java.util.*

interface IEditContactPresenter {

    fun attachView(editContactView: IEditContactView)

    fun detachView()

    fun loadContact(contactId: UUID)

    fun updateContact(contactsModel: ContactModel)

    fun onBackClicked()

    fun onPhotoImageClicked()

    fun photoUriLoaded(photoUri: String)

    fun itemAddNumberClicked()

    fun itemAddSocialClicked()

    fun onDestroy()
}
package com.zhukov.android.myapplication.presentation.listcontact.presenter

import androidx.lifecycle.LifecycleObserver
import com.zhukov.android.myapplication.presentation.listcontact.view.IContactListView
import java.util.*

interface IContactListPresenter : LifecycleObserver {

    fun attachView(iContactListView: IContactListView)

    fun detachView()

    fun onStartView()

    fun onItemClicked(contactId: UUID)

    fun loadContactList()

    fun onItemMenuEdit(contactId: UUID)

    fun deleteContact(contactId: UUID)

    fun addNewContact()

    fun updateContactListSecondName()

    fun updateContactListFirstName()

    fun onDestroy()

}
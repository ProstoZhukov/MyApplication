package com.zhukov.android.myapplication.presentation.infocontact.presenter

import com.zhukov.android.myapplication.presentation.infocontact.view.IContactView
import java.util.*

 interface IContactPresenter {
    fun attachView(iContactView: IContactView)
    fun detachView()
    fun itemEditClicked()
    fun onBackClicked()
    fun onDestroy()
    fun loadContact(contactId: UUID)
}
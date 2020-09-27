package com.zhukov.android.myapplication

import android.app.Application
import com.zhukov.android.myapplication.di.application.AppComponent
import com.zhukov.android.myapplication.di.application.AppModule
import com.zhukov.android.myapplication.di.application.DaggerAppComponent
import com.zhukov.android.myapplication.di.editorcontact.EditContactComponent
import com.zhukov.android.myapplication.di.editorcontact.EditContactModule
import com.zhukov.android.myapplication.di.infocontact.ContactComponent
import com.zhukov.android.myapplication.di.infocontact.ContactModule
import com.zhukov.android.myapplication.di.listcontact.ContactListComponent
import com.zhukov.android.myapplication.di.listcontact.ContactListModule

class App() : Application() {
    private var mAppComponent: AppComponent? = null
    private var mContactListComponent: ContactListComponent? = null
    private var mEditContactComponent: EditContactComponent? = null
    private var mContactComponent: ContactComponent? = null

    companion object {
        private lateinit var mApp: App
        fun get(): App {
            return mApp
        }
    }

    init {
        mApp = this
    }

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }

    val appComponent: AppComponent?
        get() = mAppComponent

    fun plusContactListModule(contactsListModule: ContactListModule): ContactListComponent? {
        if (mContactListComponent == null) {
            mContactListComponent = appComponent?.plus(contactsListModule)
        }
        return mContactListComponent
    }

    fun clearContactsListComponent() {
        mContactListComponent = null
    }

    fun plusContactModule(contactModule: ContactModule): ContactComponent? {
        if (mContactComponent == null) {
            mContactComponent = appComponent?.plus(contactModule)
        }
        return mContactComponent
    }

    fun clearContactComponent() {
        mContactComponent = null
    }

    fun plusEditContactModule(editContactModule: EditContactModule): EditContactComponent? {
        if (mEditContactComponent == null) {
            mEditContactComponent = appComponent?.plus(editContactModule)
        }
        return mEditContactComponent
    }

    fun clearEditContactComponent() {
        mEditContactComponent = null
    }
}
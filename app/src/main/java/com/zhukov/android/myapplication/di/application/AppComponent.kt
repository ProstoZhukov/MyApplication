package com.zhukov.android.myapplication.di.application

import com.zhukov.android.myapplication.di.editorcontact.EditContactComponent
import com.zhukov.android.myapplication.di.editorcontact.EditContactModule
import com.zhukov.android.myapplication.di.infocontact.ContactComponent
import com.zhukov.android.myapplication.di.infocontact.ContactModule
import com.zhukov.android.myapplication.di.listcontact.ContactListComponent
import com.zhukov.android.myapplication.di.listcontact.ContactListModule
import com.zhukov.android.myapplication.presentation.listcontact.hostcontact.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun plus(contactsListModule: ContactListModule): ContactListComponent
    fun plus(editContactModule: EditContactModule): EditContactComponent
    fun plus(contactModule: ContactModule): ContactComponent
}
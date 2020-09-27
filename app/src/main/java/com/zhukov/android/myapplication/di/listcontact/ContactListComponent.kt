package com.zhukov.android.myapplication.di.listcontact

import com.zhukov.android.myapplication.presentation.listcontact.view.ContactListFragment
import dagger.Subcomponent

@Subcomponent(modules = [ContactListModule::class])
@ContactListScope
interface ContactListComponent {
    fun inject(contactListFragment: ContactListFragment)
}
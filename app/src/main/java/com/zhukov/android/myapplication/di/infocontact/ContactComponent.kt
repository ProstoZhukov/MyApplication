package com.zhukov.android.myapplication.di.infocontact

import com.zhukov.android.myapplicationlist.presentation.infocontact.view.ContactFragment
import dagger.Subcomponent

@Subcomponent(modules = [ContactModule::class])
@ContactScope
interface ContactComponent {
    fun inject(contactFragment: ContactFragment)
}
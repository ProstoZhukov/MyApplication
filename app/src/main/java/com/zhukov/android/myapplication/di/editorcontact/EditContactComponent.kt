package com.zhukov.android.myapplication.di.editorcontact

import com.zhukov.android.myapplication.presentation.editorcontact.view.EditContactFragment
import dagger.Subcomponent


@Subcomponent(modules = [EditContactModule::class])
@EditContactScope
interface EditContactComponent {
    fun inject(editContactFragment: EditContactFragment)
}
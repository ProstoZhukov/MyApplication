package com.zhukov.android.myapplication.di.editorcontact

import com.zhukov.android.myapplication.busines.editcontact.EditContactInteractor
import com.zhukov.android.myapplication.busines.editcontact.IEditContactInteractor
import com.zhukov.android.myapplication.data.database.IDataBaseProvider
import com.zhukov.android.myapplication.data.repositories.editcontact.EditContactRepository
import com.zhukov.android.myapplication.data.repositories.editcontact.IEditContactRepository
import com.zhukov.android.myapplication.presentation.editorcontact.presenter.EditContactPresenter
import com.zhukov.android.myapplication.presentation.editorcontact.presenter.IEditContactPresenter
import dagger.Module
import dagger.Provides


@Module
class EditContactModule {

    @Provides
    @EditContactScope
    fun provideEditContactPresenter(editContactInteractor: IEditContactInteractor): IEditContactPresenter {
        return EditContactPresenter(editContactInteractor)
    }

    @Provides
    @EditContactScope
    fun provideEditContactInteractor(contactsListRepository: IEditContactRepository): IEditContactInteractor {
        return EditContactInteractor(contactsListRepository)
    }

    @Provides
    @EditContactScope
    fun provideEditContactRepository(dataBaseProvider: IDataBaseProvider): IEditContactRepository {
        return EditContactRepository(dataBaseProvider)
    }
}
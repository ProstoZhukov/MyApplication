package com.zhukov.android.myapplication.di.listcontact

import com.zhukov.android.myapplication.busines.listcontact.ContactListInteractor
import com.zhukov.android.myapplication.busines.listcontact.IContactListInteractor
import com.zhukov.android.myapplication.data.database.IDataBaseProvider
import com.zhukov.android.myapplication.data.repositories.listcontact.ContactListRepository
import com.zhukov.android.myapplication.data.repositories.listcontact.IContactListRepository
import com.zhukov.android.myapplication.presentation.listcontact.presenter.ContactListPresenter
import com.zhukov.android.myapplication.presentation.listcontact.presenter.IContactListPresenter
import dagger.Module
import dagger.Provides

@Module
class ContactListModule {
    @Provides
    @ContactListScope
    fun provideContactListPresenter(contactListInteractor: IContactListInteractor): IContactListPresenter {
        return ContactListPresenter(contactListInteractor)
    }

    @Provides
    @ContactListScope
    fun provideContactsListInteractor(contactsListRepository: IContactListRepository): IContactListInteractor {
        return ContactListInteractor(contactsListRepository)
    }

    @Provides
    @ContactListScope
    fun provideContactsListRepository(dataBaseProvider: IDataBaseProvider): IContactListRepository {
        return ContactListRepository(dataBaseProvider)
    }
}
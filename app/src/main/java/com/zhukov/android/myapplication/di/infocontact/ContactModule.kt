package com.zhukov.android.myapplication.di.infocontact

import com.zhukov.android.myapplication.busines.infocontact.ContactInteractor
import com.zhukov.android.myapplication.busines.infocontact.IContactInteractor
import com.zhukov.android.myapplication.data.database.IDataBaseProvider
import com.zhukov.android.myapplication.data.repositories.infocontact.ContactRepository
import com.zhukov.android.myapplication.data.repositories.infocontact.IContactRepository
import com.zhukov.android.myapplication.presentation.infocontact.presenter.ContactPresenter
import com.zhukov.android.myapplication.presentation.infocontact.presenter.IContactPresenter
import dagger.Module
import dagger.Provides

@Module
class ContactModule {
    @Provides
    @ContactScope
    fun provideContactPresenter(contactInteractor: IContactInteractor): IContactPresenter {
        return ContactPresenter(contactInteractor)
    }

    @Provides
    @ContactScope
    fun provideContactInteractor(contactRepository: IContactRepository): IContactInteractor {
        return ContactInteractor(contactRepository)
    }

    @Provides
    @ContactScope
    fun provideContactRepository(dataBaseProvider: IDataBaseProvider): IContactRepository {
        return ContactRepository(dataBaseProvider)
    }
}

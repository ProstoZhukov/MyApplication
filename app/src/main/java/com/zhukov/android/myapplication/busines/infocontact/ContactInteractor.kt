package com.zhukov.android.myapplication.busines.infocontact

import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.data.repositories.infocontact.IContactRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class ContactInteractor(iContactRepository: IContactRepository) :
    IContactInteractor {
    private val mRepository: IContactRepository = iContactRepository

    override fun loadContact(contactId: UUID): Single<ContactModel> {
        return mRepository.loadContact(contactId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
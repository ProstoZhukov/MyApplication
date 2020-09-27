package com.zhukov.android.myapplication.busines.listcontact

import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.data.repositories.listcontact.IContactListRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class ContactListInteractor(contactListRepository: IContactListRepository) :
    IContactListInteractor {

    private val mContactListRepository: IContactListRepository = contactListRepository

    override fun addNewContact(): Single<UUID> {
        return mContactListRepository.addNewContact()
    }

    override fun deleteContact(contactId: UUID): Completable {
        return mContactListRepository.deleteContact(contactId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun loadContactList(): Single<List<ContactModel>> {
        return mContactListRepository.loadContactList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
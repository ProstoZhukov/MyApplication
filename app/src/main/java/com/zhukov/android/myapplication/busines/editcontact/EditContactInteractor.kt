package com.zhukov.android.myapplication.busines.editcontact

import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.data.repositories.editcontact.IEditContactRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class EditContactInteractor(editContactRepository: IEditContactRepository) :
    IEditContactInteractor {
    var mEditContactRepository: IEditContactRepository = editContactRepository

    override fun loadContact(contactId: UUID): Single<ContactModel> {
        return mEditContactRepository.loadContact(contactId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateContact(contactModel: ContactModel): Completable {
        return mEditContactRepository.updateContact(contactModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
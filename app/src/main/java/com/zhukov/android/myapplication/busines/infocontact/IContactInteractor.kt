package com.zhukov.android.myapplication.busines.infocontact

import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Single
import java.util.*

interface IContactInteractor {
    fun loadContact(contactId: UUID): Single<ContactModel>
}
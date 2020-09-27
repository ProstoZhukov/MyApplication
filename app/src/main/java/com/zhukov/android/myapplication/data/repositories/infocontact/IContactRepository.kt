package com.zhukov.android.myapplication.data.repositories.infocontact

import com.zhukov.android.myapplication.data.database.model.ContactModel
import io.reactivex.Single
import java.util.*

interface IContactRepository {
    fun loadContact(contactId: UUID): Single<ContactModel>
}
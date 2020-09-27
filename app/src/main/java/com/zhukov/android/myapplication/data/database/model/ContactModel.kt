package com.zhukov.android.myapplication.data.database.model

import java.util.*

class ContactModel {

    var mContactId: UUID
        private set
    var mContactFirstName: String? = null
    var mContactLastName: String? = null
    var mPatronymic: String? = null
    var mContactPhoto: String? = null
    var mContactMainNumber: String? = null
    var mContactSecondNumber: String = ""
    var mContactSecondNumber2: String = ""
    var mContactSocialMedia: String = ""
    var mContactSocialMedia2: String = ""
    var mContactSocialMedia3: String = ""
    var mContactInformation: String = ""

    constructor()
    {
        mContactId = UUID.randomUUID();
    }
    constructor(id: UUID){
        mContactId = id
    }
}







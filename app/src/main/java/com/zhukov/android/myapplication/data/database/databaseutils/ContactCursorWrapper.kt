package com.zhukov.android.myapplication.data.database.databaseutils

import android.database.Cursor
import android.database.CursorWrapper
import com.zhukov.android.myapplication.data.database.databaseutils.ContactDbSchema.ContactTable
import com.zhukov.android.myapplication.data.database.model.ContactModel
import java.util.*

class ContactCursorWrapper(cursor: Cursor?): CursorWrapper(cursor) {

    fun getContactModel(): ContactModel {
        var uuidString =
            getString(getColumnIndex(ContactTable.Cols.UUID))
        var contactFirstName =
            getString(getColumnIndex(ContactTable.Cols.FIRST_NAME))
        var contactLastName =
            getString(getColumnIndex(ContactTable.Cols.LAST_NAME))
        var patronymic =
            getString(getColumnIndex(ContactTable.Cols.PATRONYMIC))
        var contactPhoto =
            getString(getColumnIndex(ContactTable.Cols.PHOTO_URI))
        var contactMainNumber =
            getString(getColumnIndex(ContactTable.Cols.MAIN_NUMBER))
        var contactSecondNumber =
            getString(getColumnIndex(ContactTable.Cols.SECOND_NUMBER))
        var contactSecondNumber2 =
            getString(getColumnIndex(ContactTable.Cols.SECOND_NUMBER2))
        var contactSocialMedia =
            getString(getColumnIndex(ContactTable.Cols.SOCIAL_MEDIA))
        var contactSocialMedia2 =
            getString(getColumnIndex(ContactTable.Cols.SOCIAL_MEDIA2))
        var contactSocialMedia3 =
            getString(getColumnIndex(ContactTable.Cols.SOCIAL_MEDIA3))
        var contactInformation =
            getString(getColumnIndex(ContactTable.Cols.ADDITIONAL_INFORMATION))

        var contactModel = ContactModel(UUID.fromString(uuidString))
        contactModel.mContactFirstName = contactFirstName
        contactModel.mContactLastName = contactLastName
        contactModel.mPatronymic = patronymic
        contactModel.mContactPhoto = contactPhoto
        contactModel.mContactMainNumber = contactMainNumber
        contactModel.mContactSecondNumber = contactSecondNumber
        contactModel.mContactSecondNumber2 = contactSecondNumber2
        contactModel.mContactSocialMedia = contactSocialMedia
        contactModel.mContactSocialMedia2 = contactSocialMedia2
        contactModel.mContactSocialMedia3 = contactSocialMedia3
        contactModel.mContactInformation = contactInformation

        return contactModel
    }
}





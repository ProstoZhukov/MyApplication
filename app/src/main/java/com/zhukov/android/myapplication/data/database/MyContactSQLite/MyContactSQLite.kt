package com.zhukov.android.myapplication.data.database.MyContactSQLite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.zhukov.android.myapplication.data.database.databaseutils.ContactBaseHelper
import com.zhukov.android.myapplication.data.database.databaseutils.ContactCursorWrapper
import com.zhukov.android.myapplication.data.database.databaseutils.ContactDbSchema.ContactTable
import com.zhukov.android.myapplication.data.database.model.ContactModel
import java.util.*

class MyContactSQLite(contactBaseHelper: ContactBaseHelper) : IMyContactSQlite {

    private var mDatabase: SQLiteDatabase

    init {
        mDatabase = contactBaseHelper.writableDatabase
    }

    private fun getContentValues(contactModel: ContactModel): ContentValues {
        val values = ContentValues()
        values.put(ContactTable.Cols.UUID, contactModel.mContactId.toString())
        values.put(ContactTable.Cols.FIRST_NAME, contactModel.mContactFirstName)
        values.put(ContactTable.Cols.LAST_NAME, contactModel.mContactLastName)
        values.put(ContactTable.Cols.PATRONYMIC, contactModel.mPatronymic)
        values.put(ContactTable.Cols.PHOTO_URI, contactModel.mContactPhoto)
        values.put(ContactTable.Cols.MAIN_NUMBER, contactModel.mContactMainNumber)
        values.put(ContactTable.Cols.SECOND_NUMBER, contactModel.mContactSecondNumber)
        values.put(ContactTable.Cols.SECOND_NUMBER2, contactModel.mContactSecondNumber2)
        values.put(ContactTable.Cols.SOCIAL_MEDIA, contactModel.mContactSocialMedia)
        values.put(ContactTable.Cols.SOCIAL_MEDIA2, contactModel.mContactSocialMedia2)
        values.put(ContactTable.Cols.SOCIAL_MEDIA3, contactModel.mContactSocialMedia3)
        values.put(ContactTable.Cols.ADDITIONAL_INFORMATION, contactModel.mContactInformation)

        return values
    }

    private fun queryContacts(db: SQLiteDatabase, whereClause: String?, whereArgs: Array<String>?): ContactCursorWrapper {
        val cursor = db.query(
            ContactTable.NAME,
            null,  // columns - с null выбираются все столбцы
            whereClause,
            whereArgs,
            null,  // groupBy
            null,  // having
            null // orderBy
        )
        return ContactCursorWrapper(cursor)
    }

    override fun deleteContact(contactId: UUID) {
        mDatabase.delete(ContactTable.NAME,
            ContactTable.Cols.UUID + " = ?",
            arrayOf(contactId.toString()))
    }

    override fun addContact(): UUID {
        var contactModel: ContactModel = ContactModel(UUID.randomUUID())
        var contentValues: ContentValues = getContentValues(contactModel)
        mDatabase.insert(ContactTable.NAME,null,contentValues)
        return contactModel.mContactId
    }

    override fun getContacts(): List<ContactModel> {
        val cursorWrapper: ContactCursorWrapper = queryContacts(mDatabase,
        null,
        null)
        val contactModelList = arrayListOf<ContactModel>()

        try {
            cursorWrapper.moveToFirst()
            while (!cursorWrapper.isAfterLast) {
                contactModelList.add(cursorWrapper.getContactModel())
                cursorWrapper.moveToNext()
            }
        } finally {
            cursorWrapper.close()
        }
        return contactModelList
    }

    override fun getContact(contactId: UUID): ContactModel? {
        val uuidString: String = contactId.toString()
        val cursorWrapper: ContactCursorWrapper = queryContacts(mDatabase,
        ContactTable.Cols.UUID + " = ?",
        arrayOf(uuidString))
        try {
            if (cursorWrapper.count == 0) {
                return null
            }
            cursorWrapper.moveToFirst()
            return cursorWrapper.getContactModel()
        } finally {
            cursorWrapper.close()
        }
    }

    override fun updateContact(contactModel: ContactModel) {
        val uuidString: String = contactModel.mContactId.toString()
        val values: ContentValues = getContentValues(contactModel)

        mDatabase.update(ContactTable.NAME, values,
        ContactTable.Cols.UUID + " = ?",
        arrayOf(uuidString))
    }
}




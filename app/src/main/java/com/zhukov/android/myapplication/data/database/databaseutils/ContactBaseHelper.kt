package com.zhukov.android.myapplication.data.database.databaseutils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.zhukov.android.myapplication.data.database.databaseutils.ContactDbSchema.ContactTable

class ContactBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "contactBase.db"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "create table " + ContactTable.NAME.toString() + "(" +
                    " _id integer primary key autoincrement, " +
                    ContactTable.Cols.UUID + ", " +
                    ContactTable.Cols.FIRST_NAME + ", " +
                    ContactTable.Cols.LAST_NAME + ", " +
                    ContactTable.Cols.PATRONYMIC + ", " +
                    ContactTable.Cols.PHOTO_URI + ", " +
                    ContactTable.Cols.MAIN_NUMBER + ", " +
                    ContactTable.Cols.SECOND_NUMBER + ", " +
                    ContactTable.Cols.SECOND_NUMBER2 + ", " +
                    ContactTable.Cols.SOCIAL_MEDIA + ", " +
                    ContactTable.Cols.SOCIAL_MEDIA2 + ", " +
                    ContactTable.Cols.SOCIAL_MEDIA3 + ", " +
                    ContactTable.Cols.ADDITIONAL_INFORMATION +
                    ")"
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}

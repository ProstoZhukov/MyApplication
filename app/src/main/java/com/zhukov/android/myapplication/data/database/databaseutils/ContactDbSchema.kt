package com.zhukov.android.myapplication.data.database.databaseutils

class ContactDbSchema {
    object ContactTable{
        const val NAME = "contacts"

        object Cols{
            const val UUID = "uuid"
            const val FIRST_NAME = "firstName"
            const val LAST_NAME = "lastName"
            const val PATRONYMIC = "patronymic"
            const val PHOTO_URI = "photoUri"
            const val MAIN_NUMBER = "mainNumber"
            const val SECOND_NUMBER = "secondNumber"
            const val SECOND_NUMBER2 = "secondNumber2"
            const val SOCIAL_MEDIA = "socialMedia"
            const val SOCIAL_MEDIA2 = "socialMedia2"
            const val SOCIAL_MEDIA3 = "socialMedia3"
            const val ADDITIONAL_INFORMATION = "additionalInformation"
        }
    }
}





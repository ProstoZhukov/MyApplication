package com.zhukov.android.myapplication.di.application

import android.content.Context
import com.zhukov.android.myapplication.data.database.IDataBaseProvider
import com.zhukov.android.myapplication.data.database.MyContactSQLite.IMyContactSQlite
import com.zhukov.android.myapplication.data.database.MyContactSQLite.MyContactSQLite
import com.zhukov.android.myapplication.data.database.SQLiteDbProvider
import com.zhukov.android.myapplication.data.database.databaseutils.ContactBaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(context: Context) {
    private val mContext: Context = context

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mContext
    }

    @Provides
    @Singleton
    fun provideMyContactSQLite(context: Context): IMyContactSQlite {
        return MyContactSQLite(ContactBaseHelper(context))
    }

    @Provides
    @Singleton
    fun provideDateBaseProvider(myContactSQLite: IMyContactSQlite) : IDataBaseProvider {
        return SQLiteDbProvider(myContactSQLite)
    }

}
package com.zhukov.android.myapplication.presentation.infocontact.presenter

import android.util.Log
import com.zhukov.android.myapplication.busines.infocontact.IContactInteractor
import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.presentation.infocontact.view.IContactView
import io.reactivex.disposables.CompositeDisposable
import java.util.*

 class ContactPresenter(iContactInteractor: IContactInteractor) :
    IContactPresenter {
    private var mView: IContactView? = null
    private val mInteractor: IContactInteractor = iContactInteractor
    private var mContactModel: ContactModel? = null
    private val mDisposer = CompositeDisposable()
    override fun attachView(iContactView: IContactView) {
        mView = iContactView
    }

    override fun detachView() {
        mView = null
    }

    override fun loadContact(contactId: UUID) {
        if (mContactModel == null) {
            mDisposer.add(
                mInteractor.loadContact(contactId)
                    .subscribe(
                        { contactModel: ContactModel -> this.onSuccess(contactModel) },
                        { throwable: Throwable -> onError(throwable) })
            )
        } else {
            mView?.updateUI(mContactModel!!)
        }
    }

    override fun itemEditClicked() {
        mView?.openEditContactScreen()
    }

    override fun onBackClicked() {
        mView?.goToListContacts()
    }

    private fun onSuccess(contactModel: ContactModel) {
        mContactModel = contactModel
        mView?.updateUI(mContactModel!!)
    }

    private fun onError(throwable: Throwable) {
        Log.e(
            TAG,
            javaClass.simpleName + " onError" + throwable
        )
    }

    override fun onDestroy() {
        mDisposer.dispose()
    }

    companion object {
        private const val TAG = "MY_TAG"
    }

}
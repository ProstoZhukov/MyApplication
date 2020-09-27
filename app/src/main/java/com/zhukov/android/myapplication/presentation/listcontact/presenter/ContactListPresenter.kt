package com.zhukov.android.myapplication.presentation.listcontact.presenter

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.zhukov.android.myapplication.busines.listcontact.IContactListInteractor
import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.presentation.listcontact.view.IContactListView
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class ContactListPresenter(listInteractor: IContactListInteractor) : IContactListPresenter {
    private val TAG = "MY_TAG"

    private var mDisposer: CompositeDisposable? = null

    private val mModelList: List<ContactModel>? = null


    var mView: IContactListView? = null

    private var mInteractor: IContactListInteractor = listInteractor

    override fun attachView(iContactListView: IContactListView) {
        mView = iContactListView
        mDisposer = CompositeDisposable()
    }

    override fun detachView() {
        mView = null
        mDisposer!!.dispose()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStartView() {
        if (mModelList == null) {
            mDisposer!!.add(
                mInteractor.loadContactList()
                    .subscribe({ contactsList: List<ContactModel> ->
                        onLoadSuccess(
                            contactsList
                        )
                    }, { throwable: Throwable -> onError(throwable) })
            )
        } else {
            mView!!.updateContactList(mModelList)
        }
    }

    override fun onItemClicked(contactId: UUID) {
        mView!!.openContactScreen(contactId)
    }

    override fun onItemMenuEdit(contactId: UUID) {
        mView!!.openEditContactScreen(contactId)
    }


    override fun addNewContact() {
        mDisposer!!.add(
            mInteractor.addNewContact()
                .subscribe(
                    { contactId: UUID -> onAddSuccess(contactId) },
                    { throwable: Throwable -> onError(throwable) })
        )
    }

    override fun deleteContact(contactId: UUID) {
        mDisposer!!.add(
            mInteractor.deleteContact(contactId)
                .subscribe(
                    { onDeleteSuccess() },
                    { throwable: Throwable -> onError(throwable) })
        )
    }

    override fun loadContactList() {
        mDisposer!!.add(
            mInteractor.loadContactList()
                .subscribe({ contactsList: List<ContactModel> ->
                    onLoadSuccess(
                        contactsList
                    )
                }, { throwable: Throwable -> onError(throwable) })
        )
    }

    override fun updateContactListSecondName() {
        mDisposer!!.add(
            mInteractor.loadContactList()
                .subscribe({ contactsList: List<ContactModel> ->
                    onLoadUpdateSecondSuccess(
                        contactsList
                    )
                }, { throwable: Throwable -> onError(throwable) })
        )
    }

    override fun updateContactListFirstName() {
        mDisposer!!.add(
            mInteractor.loadContactList()
                .subscribe({ contactsList: List<ContactModel> ->
                    onLoadUpdateFirstSuccess(
                        contactsList
                    )
                }, { throwable: Throwable -> onError(throwable) })
        )
    }

    private fun onLoadSuccess(contactsList: List<ContactModel>) {
        mView!!.updateContactList(contactsList)
    }

    private fun onLoadUpdateSecondSuccess(contactsList: List<ContactModel>) {
        mView!!.updateContactListSecondName(contactsList)
    }

    private fun onLoadUpdateFirstSuccess(contactsList: List<ContactModel>) {
        mView!!.updateContactListFirstName(contactsList)
    }

    private fun onAddSuccess(contactId: UUID) {
        mView!!.openEditContactScreen(contactId)
    }

    private fun onDeleteSuccess() {
        Log.i(TAG, javaClass.simpleName + " contact deleted")
    }

    private fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override fun onDestroy() {
        mDisposer!!.dispose()
    }
}
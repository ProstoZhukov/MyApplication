package com.zhukov.android.myapplication.presentation.editorcontact.presenter

import android.util.Log
import com.zhukov.android.myapplication.busines.editcontact.IEditContactInteractor
import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.presentation.editorcontact.view.IEditContactView
import io.reactivex.disposables.CompositeDisposable
import java.util.*


class EditContactPresenter(iEditContactInteractor: IEditContactInteractor) :
    IEditContactPresenter {
    private var mView: IEditContactView? = null
    private var mContactModel: ContactModel? = null
    private val mDisposer = CompositeDisposable()
    private val mInteractor: IEditContactInteractor = iEditContactInteractor

    override fun attachView(editContactView: IEditContactView) {
        mView = editContactView
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

    override fun updateContact(contactModel: ContactModel) {
        if (contactModel.mContactPhoto == null && mContactModel?.mContactPhoto != null) {
            contactModel.mContactPhoto = (mContactModel!!.mContactPhoto)
        }
        mContactModel = contactModel
        mView?.updateUI(contactModel)
        mDisposer.add(
            mInteractor.updateContact(contactModel)
                .subscribe(
                    { onUpdate() },
                    { throwable: Throwable -> onError(throwable) })
        )
    }

    override fun onPhotoImageClicked() {
        mView?.updatePhoto()
    }

    override fun photoUriLoaded(photoUri: String) {
        mContactModel?.mContactPhoto = (photoUri)
    }

    private fun onUpdate() {
        mView?.showMessageText("Контакт был успешно обновлен!")
    }

    private fun onAdd(id: UUID) {
        Log.i(TAG, " - контакт был успешно добавлен$id"
        )
    }

    private fun onSuccess(contactModel: ContactModel) {
        mView?.updateUI(contactModel)
        mContactModel = contactModel
    }

    private fun onError(throwable: Throwable) {
        Log.e(TAG, javaClass.simpleName + " onError" + throwable
        )
    }

    override fun itemAddNumberClicked() {
        mView?.itemAddNumberClicked()
    }

    override fun itemAddSocialClicked() {
        mView?.itemAddSocialClicked()
    }

    override fun onDestroy() {
        mDisposer.dispose()
    }

    companion object {
        private const val TAG = "MY_TAG"
    }

}
package com.zhukov.android.myapplication.presentation.editorcontact.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zhukov.android.myapplication.App
import com.zhukov.android.myapplication.R
import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.di.editorcontact.EditContactModule
import com.zhukov.android.myapplication.di.infocontact.ContactModule
import com.zhukov.android.myapplication.presentation.editorcontact.presenter.IEditContactPresenter
import java.util.*
import javax.inject.Inject

class EditContactFragment : Fragment(), IEditContactView {

    @Inject
    lateinit var mEditContactPresenter: IEditContactPresenter

    companion object {
        const val ARG_CONTACT_EDIT_ID = "contactId"
    }


    private var mContactModel: ContactModel? = null
    private var mContactId: UUID? = null
    private var mPhotoContact: ImageView? = null
    private var mFirstName: EditText? = null
    private var mSecondName: EditText? = null
    private var mPatronymic: EditText? = null
    private var mMainNumber: EditText? = null
    private var mSecondNumber: EditText? = null
    private var mSecondNumber2: EditText? = null
    private var mSocialMedia: EditText? = null
    private var mSocialMedia2: EditText? = null
    private var mSocialMedia3: EditText? = null
    private var mAboutContact: EditText? = null
    private var mSecondTable: TableRow? = null
    private var mSecondTable2: TableRow? = null
    private var mSocialTable2: TableRow? = null
    private var mSocialTable3: TableRow? = null
    private var mFloatingActionButton: FloatingActionButton? = null
    private var mToolbarBack: ImageView? = null
    private var mToolbarSave: ImageView? = null
    private var mToolbarTittle: TextView? = null
    private var mToolbar: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEditContactPresenter!!.attachView(this)
        if (arguments != null) {
            mContactId =
                UUID.fromString(arguments!!.getString(ARG_CONTACT_EDIT_ID))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_contact_fragment, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        mToolbar = view.findViewById(R.id.toolbar) as View
        mToolbarBack =
            view.findViewById<View>(R.id.toolbar_back) as ImageView
        mToolbarSave =
            view.findViewById<View>(R.id.toolbar_filter) as ImageView
        mToolbarSave!!.setOnClickListener { updateContact() }
        mToolbarTittle = view.findViewById<View>(R.id.toolbar_name) as TextView
        mToolbarBack!!.isClickable = true
        mToolbarBack!!.setOnClickListener {
            if (getView() != null) {
                mEditContactPresenter!!.onBackClicked()
                if (activity != null) {
                    activity!!.onBackPressed()
                }
            }
        }
        mPhotoContact =
            view.findViewById<View>(R.id.photo_contact_edit) as ImageView
        mPhotoContact!!.setOnClickListener { v: View? -> mEditContactPresenter!!.onPhotoImageClicked() }
        mPhotoContact!!.visibility = View.VISIBLE
        mFirstName = view.findViewById<View>(R.id.first_name_edit_text) as EditText
        mSecondName = view.findViewById<View>(R.id.second_name_edit_text) as EditText
        mPatronymic = view.findViewById<View>(R.id.patronymic_edit_text) as EditText
        mMainNumber = view.findViewById<View>(R.id.main_number_edit_text) as EditText
        mSecondNumber =
            view.findViewById<View>(R.id.second_number_edit_text) as EditText
        mSecondNumber2 =
            view.findViewById<View>(R.id.second_number2_edit_text) as EditText
        mSocialMedia =
            view.findViewById<View>(R.id.social_contacts_edit_text) as EditText
        mSocialMedia2 =
            view.findViewById<View>(R.id.social_contacts2_edit_text) as EditText
        mSocialMedia3 =
            view.findViewById<View>(R.id.social_contacts3_edit_text) as EditText
        mAboutContact =
            view.findViewById<View>(R.id.about_contact_edit_text) as EditText
        mSecondTable =
            view.findViewById<View>(R.id.number_table2_edit_text) as TableRow
        mSecondTable2 =
            view.findViewById<View>(R.id.number_table3_edit_text) as TableRow
        mSocialTable2 =
            view.findViewById<View>(R.id.social_table2_edit_text) as TableRow
        mSocialTable3 =
            view.findViewById<View>(R.id.social_table3_edit_text) as TableRow
        mFloatingActionButton =
            view.findViewById<View>(R.id.add_row) as FloatingActionButton
        mFloatingActionButton!!.setOnClickListener { view -> createPopupMenu(context, view) }
        if (savedInstanceState == null) {
            mEditContactPresenter!!.loadContact(mContactId!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mEditContactPresenter!!.detachView()
        App.get().clearEditContactComponent()
    }

    override fun updateUI(contactsModel: ContactModel) {
        mContactModel = contactsModel
        if (contactsModel.mContactPhoto != null) {
            mPhotoContact!!.setImageURI(Uri.parse(contactsModel.mContactPhoto))
        }
        editContact(mContactModel)
    }

    override fun updateContact() {
        if (mMainNumber!!.text.toString() == "") {
            mMainNumber!!.setHintTextColor(Color.RED)
        } else {
            val contactModel = mContactId?.let { ContactModel(it) }
            if (contactModel != null) {
                contactModel.mContactFirstName = (mFirstName!!.text.toString())
            }
            if (contactModel != null) {
                contactModel.mContactLastName = (mSecondName!!.text.toString())
            }
            if (contactModel != null) {
                contactModel.mPatronymic = (mPatronymic!!.text.toString())
            }
            if (contactModel != null) {
                contactModel.mContactMainNumber = (mMainNumber!!.text.toString())
            }
            if (contactModel != null) {
                contactModel.mContactSecondNumber = (mSecondNumber!!.text.toString())
            }
            if (contactModel != null) {
                contactModel.mContactSecondNumber2 = (mSecondNumber2!!.text.toString())
            }
            if (contactModel != null) {
                contactModel.mContactSocialMedia = (mSocialMedia!!.text.toString())
            }
            if (contactModel != null) {
                contactModel.mContactSocialMedia2 = (mSocialMedia2!!.text.toString())
            }
            if (contactModel != null) {
                contactModel.mContactSocialMedia3 = (mSocialMedia3!!.text.toString())
            }
            if (contactModel != null) {
                contactModel.mContactInformation = (mAboutContact!!.text.toString())
            }
            if (contactModel != null) {
                mEditContactPresenter?.updateContact(contactModel)
            }
        }
    }

    override fun updatePhoto() {
        startActivityForResult(
            Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ), 100
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data != null) {
            val uri = data.data
            mPhotoContact!!.setImageURI(uri)
            mEditContactPresenter?.photoUriLoaded(uri.toString())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun editContact(contactsModel: ContactModel?) {
        if (contactsModel != null) {
            mFirstName?.setText(contactsModel.mContactFirstName)
            mSecondName?.setText(contactsModel.mContactLastName)
            mPatronymic?.setText(contactsModel.mPatronymic)
            mMainNumber?.setText(contactsModel.mContactMainNumber)
            mSocialMedia?.setText(contactsModel.mContactSocialMedia)
            mAboutContact?.setText(contactsModel.mContactInformation)
        }
        val contactPhoto: String? = contactsModel?.mContactPhoto
        if (contactPhoto == null) {
            mPhotoContact!!.setImageResource(R.drawable.avatar)
        } else {
            mPhotoContact!!.setImageURI(Uri.parse(contactPhoto))
        }
        val contactSecondNumber: String? = contactsModel?.mContactSecondNumber
        if (contactSecondNumber == "") {
            mSecondTable!!.visibility = View.GONE
        } else {
            mSecondNumber!!.setText(contactSecondNumber)
        }
        val contactSecondNumber2: String? = contactsModel?.mContactSecondNumber2
        if (contactSecondNumber2 == "") {
            mSecondTable2!!.visibility = View.GONE
        } else {
            mSecondNumber2!!.setText(contactSecondNumber2)
        }
        val contactSocialMedia2: String? = contactsModel?.mContactSocialMedia2
        if (contactSocialMedia2 == "") {
            mSocialTable2!!.visibility = View.GONE
        } else {
            mSocialMedia2!!.setText(contactSocialMedia2)
        }
        val contactSocialMedia3: String? = contactsModel?.mContactSocialMedia3
        if (contactSocialMedia3 == "") {
            mSocialTable3!!.visibility = View.GONE
        } else {
            mSocialMedia3!!.setText(contactSocialMedia3)
        }
    }

    override fun itemAddNumberClicked() {
        if (mSecondTable!!.visibility == View.GONE) {
            mSecondTable!!.visibility = View.VISIBLE
        } else if (mSecondTable!!.visibility == View.VISIBLE) {
            mSecondTable2!!.visibility = View.VISIBLE
        }
    }

    override fun itemAddSocialClicked() {
        if (mSocialTable2!!.visibility == View.GONE) {
            mSocialTable2!!.visibility = View.VISIBLE
        } else if (mSocialTable2!!.visibility == View.VISIBLE) {
            mSocialTable3!!.visibility = View.VISIBLE
        }
    }

    private fun createPopupMenu(context: Context?, view: View) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.edit_fb_menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.mnu_fb_social -> {
                    mEditContactPresenter?.itemAddSocialClicked()
                    true
                }
                R.id.mnu_fb_number -> {
                    mEditContactPresenter?.itemAddNumberClicked()
                    true
                }
                else -> false
            }
        }
    }

    init {
        App.get().plusEditContactModule(EditContactModule())?.inject(this)
    }
}



package com.zhukov.android.myapplicationlist.presentation.infocontact.view

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.zhukov.android.myapplication.App
import com.zhukov.android.myapplication.R
import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.di.infocontact.ContactModule
import com.zhukov.android.myapplication.presentation.editorcontact.view.EditContactFragment

import com.zhukov.android.myapplication.presentation.infocontact.presenter.IContactPresenter
import com.zhukov.android.myapplication.presentation.infocontact.view.IContactView
import java.util.*
import javax.inject.Inject
 class ContactFragment : Fragment(), IContactView {
    private val mContactModel: ContactModel? = null

    @Inject
    lateinit var mPresenter: IContactPresenter
    private var mContactId: UUID? = null
    private var mPhotoContact: ImageView? = null
    private var mFirstName: TextView? = null
    private var mSecondName: TextView? = null
    private var mPatronymic: TextView? = null
    private var mMainNumber: TextView? = null
    private var mSecondNumber: TextView? = null
    private var mSecondNumber2: TextView? = null
    private var mSocialMedia: TextView? = null
    private var mSocialMedia2: TextView? = null
    private var mSocialMedia3: TextView? = null
    private var mAboutContact: TextView? = null
    private var mSecondTable: TableRow? = null
    private var mSecondTable2: TableRow? = null
    private var mSocialTable2: TableRow? = null
    private var mSocialTable3: TableRow? = null
    private var mToolbarBack: ImageView? = null
    private var mToolbarEdit: ImageView? = null
    private var mToolbarTittle: TextView? = null
    private var mToolbar: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mContactId =
                UUID.fromString(arguments!!.getString(ARG_CONTACT_ID))
        }
        mPresenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_contact_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mToolbar = view.findViewById(R.id.toolbar) as View
        mToolbarBack =
            view.findViewById<View>(R.id.toolbar_back) as ImageView
        mToolbarEdit =
            view.findViewById<View>(R.id.toolbar_edit) as ImageView
        mToolbarEdit!!.setOnClickListener { mPresenter.itemEditClicked() }
        mToolbarTittle = view.findViewById<View>(R.id.toolbar_name) as TextView
        mToolbarBack!!.isClickable = true
        mToolbarBack!!.setOnClickListener { mPresenter.onBackClicked() }
        mPhotoContact =
            view.findViewById<View>(R.id.photo_contact) as ImageView
        mFirstName = view.findViewById<View>(R.id.first_name) as TextView
        mSecondName = view.findViewById<View>(R.id.second_name) as TextView
        mPatronymic = view.findViewById<View>(R.id.patronymic) as TextView
        mMainNumber = view.findViewById<View>(R.id.main_number) as TextView
        mSecondNumber = view.findViewById<View>(R.id.second_number) as TextView
        mSecondNumber2 = view.findViewById<View>(R.id.second_number2) as TextView
        mSocialMedia = view.findViewById<View>(R.id.social_contacts) as TextView
        mSocialMedia2 = view.findViewById<View>(R.id.social_contacts2) as TextView
        mSocialMedia3 = view.findViewById<View>(R.id.social_contacts3) as TextView
        mAboutContact = view.findViewById<View>(R.id.about_contact) as TextView
        mSecondTable =
            view.findViewById<View>(R.id.number_table2) as TableRow
        mSecondTable2 =
            view.findViewById<View>(R.id.number_table3) as TableRow
        mSocialTable2 =
            view.findViewById<View>(R.id.social_table2) as TableRow
        mSocialTable3 =
            view.findViewById<View>(R.id.social_table3) as TableRow
        mContactId?.let { mPresenter.loadContact(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
        App.get().clearContactComponent()
    }

    override fun openEditContactScreen() {
        if (view != null) {
            val args = Bundle()
            args.putString(EditContactFragment.ARG_CONTACT_EDIT_ID, mContactId.toString())
            Navigation.findNavController(view!!).navigate(R.id.editContactFragment, args)
        }
    }

    override fun goToListContacts() {
        if (activity != null) {
            activity!!.onBackPressed()
        }
    }

    override fun updateUI(contactsModel: ContactModel) {
        showContact(contactsModel)
    }

    private fun showContact(contactModel: ContactModel) {
        val contactFirstName: String? = contactModel.mContactFirstName
        if (contactFirstName == "") {
            mFirstName!!.visibility = View.GONE
        } else {
            mFirstName!!.text = contactFirstName
        }
        val contactLastName: String? = contactModel.mContactLastName
        if (contactLastName == "") {
            mSecondName!!.visibility = View.GONE
        } else {
            mSecondName!!.text = contactLastName
        }
        val patronymic: String? = contactModel.mPatronymic
        if (patronymic == "") {
            mPatronymic!!.visibility = View.GONE
        } else {
            mPatronymic!!.text = contactLastName
        }
        val contactPhoto: String? = contactModel.mContactPhoto
        if (contactPhoto == null) {
            mPhotoContact!!.setImageResource(R.drawable.avatar)
        } else {
            mPhotoContact!!.setImageURI(Uri.parse(contactPhoto))
        }
        val contactMainNumber: String? = contactModel.mContactMainNumber
        if (contactMainNumber == "") {
            mMainNumber?.setText(R.string.unknown_number_now)
            mMainNumber!!.setTextColor(Color.RED)
        } else {
            mMainNumber!!.text = contactMainNumber
            mMainNumber!!.setTextColor(Color.BLUE)
        }
        val contactSecondNumber: String = contactModel.mContactSecondNumber
        if (contactSecondNumber == "") {
            mSecondTable!!.visibility = View.GONE
        } else {
            mSecondNumber!!.text = contactSecondNumber
            mSecondNumber!!.setTextColor(Color.BLUE)
        }
        val contactSecondNumber2: String = contactModel.mContactSecondNumber2
        if (contactSecondNumber2 == "") {
            mSecondTable2!!.visibility = View.GONE
        } else {
            mSecondNumber2!!.text = contactSecondNumber2
            mSecondNumber2!!.setTextColor(Color.BLUE)
        }
        val contactSocialMedia: String = contactModel.mContactSocialMedia
        if (contactSocialMedia == "") {
            mSocialMedia?.setText(R.string.unknown_info)
        } else {
            mSocialMedia!!.text = contactSocialMedia
            mSocialMedia!!.setTextColor(Color.BLUE)
        }
        val contactSocialMedia2: String = contactModel.mContactSocialMedia2
        if (contactSocialMedia2 == "") {
            mSocialTable2!!.visibility = View.GONE
        } else {
            mSocialMedia2!!.text = contactSocialMedia2
            mSocialMedia2!!.setTextColor(Color.BLUE)
        }
        val contactSocialMedia3: String = contactModel.mContactSocialMedia3
        if (contactSocialMedia3 == "") {
            mSocialTable3!!.visibility = View.GONE
        } else {
            mSocialMedia3!!.text = contactSocialMedia3
            mSocialMedia3!!.setTextColor(Color.BLUE)
        }
        val contactInformation: String? = contactModel.mContactInformation
        if (contactInformation == "") {
            mAboutContact?.setText(R.string.unknown_info)
        } else {
            mAboutContact!!.text = contactInformation
        }
    }

    companion object {
        const val ARG_CONTACT_ID = "contactId"
    }

    init {
        App.get().plusContactModule(ContactModule())?.inject(this)
    }
}
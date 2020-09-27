package com.zhukov.android.myapplication.presentation.listcontact.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zhukov.android.myapplication.App
import com.zhukov.android.myapplication.R
import com.zhukov.android.myapplication.data.database.model.ContactModel
import com.zhukov.android.myapplication.di.listcontact.ContactListModule
import com.zhukov.android.myapplication.presentation.editorcontact.view.EditContactFragment
import com.zhukov.android.myapplication.presentation.listcontact.adapters.ContactAdapter
import com.zhukov.android.myapplication.presentation.listcontact.presenter.IContactListPresenter
import com.zhukov.android.myapplicationlist.presentation.infocontact.view.ContactFragment
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ContactListFragment : Fragment(), IContactListView, ContactAdapter.OnItemClickListener{

    private lateinit var mContactRecyclerView: RecyclerView
    private lateinit var mContactAdapter: ContactAdapter
    private lateinit var mFloatingActionButton: FloatingActionButton
    private lateinit var mFilter: ImageView

    @Inject
    lateinit var mPresenter: IContactListPresenter

    init {
        App.get().plusContactListModule(ContactListModule())?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this)
        lifecycle.addObserver(mPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_list_fragment, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContactRecyclerView =
            view.findViewById<View>(R.id.contact_recycler_view) as RecyclerView
        mContactRecyclerView.layoutManager = LinearLayoutManager(activity)
        mContactAdapter = ContactAdapter(this)
        mContactRecyclerView.adapter = mContactAdapter
        mFloatingActionButton =
            view.findViewById<View>(R.id.floating_button_list) as FloatingActionButton
        mFloatingActionButton.setOnClickListener { mPresenter.addNewContact() }
        mFilter =
            view.findViewById<View>(R.id.toolbar_filter) as ImageView
        mFilter.setOnClickListener { context?.let { it1 -> createPopupMenu(it1, mFilter) } }
    }

    override fun onDestroy() {
        super.onDestroy()
        App.get().clearContactsListComponent()
        mPresenter.detachView()
        lifecycle.removeObserver(mPresenter)
    }

    private fun createPopupMenu(
        context: Context,
        view: View
    ) {
        val popupMenu = PopupMenu(getContext(), view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.list_menu_filter_popupmenu, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.mnu_filter_secondName -> {
                    mPresenter.updateContactListSecondName()
                    mContactAdapter.notifyDataSetChanged()
                    return@setOnMenuItemClickListener true
                }
                R.id.mnu_filter_name -> {
                    mPresenter.updateContactListFirstName()
                    mContactAdapter.notifyDataSetChanged()
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
    }



    override fun openContactScreen(contactId: UUID) {
        if (view != null) {
            val args = Bundle()
            args.putString(ContactFragment.ARG_CONTACT_ID, contactId.toString())
            Navigation.findNavController(view!!).navigate(R.id.contactFragment, args)
        }
    }

    override fun openEditContactScreen(contactId: UUID) {
        view?.let {
            val args = Bundle().apply {
                putString(EditContactFragment.ARG_CONTACT_EDIT_ID, contactId.toString())
            }
            Navigation.findNavController(it).navigate(R.id.editContactFragment, args)
            mContactAdapter.notifyDataSetChanged()
        }
    }

    override fun updateContactList(contactModelList: List<ContactModel>) {
        mContactAdapter.updateList(contactModelList)
    }

    override fun updateContactListSecondName(modelList: List<ContactModel>): List<ContactModel> {
        val modelListUpdate: MutableList<ContactModel> = ArrayList()
        val stringArrayList = ArrayList<String>()

        for (i in modelList.indices) {
            modelList[i].mContactLastName?.let { stringArrayList.add(it) } }
        stringArrayList.sort()
        for (i in modelList.indices) {
            for (j in stringArrayList.indices) {
                if (stringArrayList[i] == modelList[j].mContactLastName) {
                    modelListUpdate.add(modelList[j])
                }
            }
        }
        mContactAdapter.updateList(modelListUpdate)
        return modelListUpdate
    }

    override fun updateContactListFirstName(modelList: List<ContactModel>): List<ContactModel> {
        val modelListUpdate: MutableList<ContactModel> = ArrayList()
        val stringArrayList = ArrayList<String>()

        for( i in modelList.indices){
            modelList[i].mContactFirstName?.let { stringArrayList.add(it) }
        }
        stringArrayList.sort()
        for(i in modelList.indices){
            for (j in stringArrayList.indices){
                if(stringArrayList[i] == modelList[j].mContactFirstName){
                    modelListUpdate.add(modelList[j])
                }
            }
        }
        mContactAdapter.updateList(modelListUpdate)
        return modelListUpdate
    }

    override fun onItemClick(contactId: UUID) {
        mPresenter.onItemClicked(contactId)
    }

    override fun onMenuItemEditClick(contactId: UUID) {
        mPresenter.onItemMenuEdit(contactId)
    }

    override fun deleteItem(contactId: UUID) {
        mPresenter.deleteContact(contactId)
        mContactAdapter.notifyDataSetChanged()
    }
}
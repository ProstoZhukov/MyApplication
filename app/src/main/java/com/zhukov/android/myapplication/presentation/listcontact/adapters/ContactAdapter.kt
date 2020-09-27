package com.zhukov.android.myapplication.presentation.listcontact.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhukov.android.myapplication.R
import com.zhukov.android.myapplication.data.database.model.ContactModel
import java.util.*

class ContactAdapter(onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    private var mContactModels: List<ContactModel>
    private var mOnItemClickListener: OnItemClickListener = onItemClickListener

    init {
        mContactModels = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_contact,
            parent,false)
        return ContactHolder(view)
    }

    override fun getItemCount(): Int {
        return mContactModels.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val context: Context = holder.itemView.context
        mContactModels.get(position).let { holder.bind(it) }
        holder.mMenuItem.setOnClickListener( View.OnClickListener { holder.createPopupMenu(
            context,
            holder.mMenuItem,
            holder
        ) })
    }

    fun updateList(contactModelList: List<ContactModel>){
        mContactModels.toMutableList().clear()
        mContactModels.toMutableList().addAll(contactModelList)
        notifyDataSetChanged()
    }




     inner class ContactHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var mContactId: UUID

        private var mPhotoItem: ImageView
        private var mNameItem: TextView
        private var mSecondNameItem: TextView
        private var mNumberItem: TextView
        var mMenuItem: ImageButton

        init {
            mMenuItem = view.findViewById(R.id.item_menu) as ImageButton
            mPhotoItem = view.findViewById(R.id.photo_item) as ImageView
            mNameItem = view.findViewById(R.id.name_item) as TextView
            mSecondNameItem = view.findViewById(R.id.second_name_item) as TextView
            mNumberItem = view.findViewById(R.id.number_item) as TextView
            itemView.setOnClickListener(this)
        }

        fun bind(contactModel: ContactModel){
            mContactId = contactModel.mContactId
            val itemName: String? = contactModel.mContactFirstName
            val itemSecondName: String? = contactModel.mContactLastName
            val itemNumber: String? = contactModel.mContactMainNumber
            val itemPhotoUri: String? = contactModel.mContactPhoto

            if (itemName == null) {
                mNameItem.setText(R.string.unknown_name)
            } else {
                mNameItem.text = itemName
            }
            if (itemSecondName == null) {
                mSecondNameItem.setText(R.string.unknown_surname)
            } else {
                mSecondNameItem.text = itemSecondName
            }
            if (itemNumber == null) {
                mNumberItem.setText(R.string.unknown_number)
            } else {
                mNumberItem.text = itemNumber
            }
            if (itemPhotoUri == null) {
                mPhotoItem.setImageResource(R.drawable.avatar)
            } else {
                mPhotoItem.setImageURI(Uri.parse(itemPhotoUri))
            }
        }


        override fun onClick(p0: View?) {
            mOnItemClickListener.onItemClick(mContactId)
        }

         fun createPopupMenu(context: Context, view: View, contactHolder: ContactHolder) {
             val popupMenu = PopupMenu(context, view)
             popupMenu.inflate(R.menu.item_option_menu)
             popupMenu.show()
             popupMenu.setOnMenuItemClickListener { item ->
                 when (item.itemId) {
                     R.id.mnu_item_edit -> {
                         mOnItemClickListener.onMenuItemEditClick(mContactId)
                         true
                     }
                     R.id.mnu_item_delete -> {
                         val newPosition = contactHolder.adapterPosition
                         notifyItemRemoved(newPosition)
                         mContactModels.toMutableList().removeAt(newPosition)
                         notifyDataSetChanged()
                         mOnItemClickListener.deleteItem(mContactId)
                         true
                     }
                     else -> false
                 }
             }
         }


    }

    interface OnItemClickListener{

        fun onItemClick(contactId: UUID)

        fun onMenuItemEditClick(contactId: UUID)

        fun deleteItem(contactId: UUID)
    }

}




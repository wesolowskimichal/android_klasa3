package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Contact

class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {
    inner class ContactsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    private val differCallback = object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.contact_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = differ.currentList[position]
        val name = holder.itemView.findViewById<TextView>(R.id.tvName)
        val number = holder.itemView.findViewById<TextView>(R.id.tvNumber)
        val cb = holder.itemView.findViewById<CheckBox>(R.id.cbSelected)
        holder.itemView.apply {
            name.text = contact.name
            number.text = contact.number
            cb.isSelected = contact.selected
            cb.isChecked = contact.selected
            setOnClickListener{
                onItemClickListener?.let {
                    it(contact)
                }
            }
        }
    }
    private var onItemClickListener : ((Contact) -> Unit)? = null
    fun setOnItemClickListener(listener: (Contact) -> Unit) {
        onItemClickListener = listener
    }
}
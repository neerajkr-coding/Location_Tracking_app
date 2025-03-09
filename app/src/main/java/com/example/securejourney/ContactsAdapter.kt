package com.example.securejourney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class ContactsAdapter(private val contactsList: MutableList<Contacts>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {

        val Inflater = LayoutInflater.from(parent.context)
        val item = Inflater.inflate(R.layout.invite_item, parent, false)

        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val i = contactsList[position]

        holder.name.setText(i.name)
        holder.number.setText(i.number.toString())
    }


    class ViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

        val name = item.findViewById<TextView>(R.id.name_invite);
        val number = item.findViewById<TextView>(R.id.add_invite);
    }


}
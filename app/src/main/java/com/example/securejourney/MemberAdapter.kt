package com.example.securejourney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(private val memberList:MutableList<Member>):
    RecyclerView.Adapter<MemberAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.item_family_member,parent,false)

        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: MemberAdapter.ViewHolder, position: Int) {

        val i = memberList[position]

        holder.name.setText(i.name)
        holder.dp.setImageResource(i.img)
        holder.location.setText(i.loc)
        holder.battery.setText(i.battery)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    class ViewHolder(private val item:View): RecyclerView.ViewHolder(item) {

        val dp = item.findViewById<ImageView>(R.id.pic)
        val name = item.findViewById<TextView>(R.id.user_name)
        val location = item.findViewById<TextView>(R.id.user_location)
        val battery = item.findViewById<TextView>(R.id.Battery_percent)

    }
}
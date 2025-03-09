package com.example.securejourney

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberList = mutableListOf<Member>(Member("Neeraj",R.drawable.ic_home,"Delhi","20%"))
        memberList.add(Member("Arpit",R.drawable.blue_gradient,"Mumbai","60%"))
        memberList.add(Member("VK",R.drawable.sos,"Himachal","80%"))
        memberList.add(Member("Vaibhav K",R.drawable.dp_icon,"Hari nagar","22%"))
        memberList.add(Member("Vaibhav",R.drawable.gaurd,"Mumbai","75%"))

        val adapter = MemberAdapter(memberList)

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        //invite RecyclerView

        val contactsList = mutableListOf<Contacts>()
        contactsList.add(Contacts("Neeraj",7011903243))
        contactsList.add(Contacts("Arpit",9990110200))
        contactsList.add(Contacts("Vk",1234567890))
        contactsList.add(Contacts("Neeraj",7011903243))
        contactsList.add(Contacts("Arpit",9990110200))
        contactsList.add(Contacts("Vk",1234567890))
        contactsList.add(Contacts("Neeraj",7011903243))
        contactsList.add(Contacts("Arpit",9990110200))
        contactsList.add(Contacts("Vk",1234567890))

        val ContactsAdapter = ContactsAdapter(contactsList)


        val inviteRecyclerview = requireView().findViewById<RecyclerView>(R.id.recyclerView_invite)
        inviteRecyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,  false)
        inviteRecyclerview.adapter = ContactsAdapter

    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
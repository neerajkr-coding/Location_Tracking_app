package com.example.securejourney

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.securejourney.Adapter.ContactsAdapter
import com.example.securejourney.Adapter.MemberAdapter
import com.example.securejourney.Entity.Contacts
import com.example.securejourney.Entity.Member


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

//        val contactsList = mutableListOf<Contacts>()
//        contactsList.add(Contacts("Neeraj",7011903243))
//        contactsList.add(Contacts("Arpit",9990110200))
//        contactsList.add(Contacts("Vk",1234567890))
//        contactsList.add(Contacts("Neeraj",7011903243))
//        contactsList.add(Contacts("Arpit",9990110200))
//        contactsList.add(Contacts("Vk",1234567890))
//        contactsList.add(Contacts("Neeraj",7011903243))
//        contactsList.add(Contacts("Arpit",9990110200))
//        contactsList.add(Contacts("Vk",1234567890))

        val contactList = fetchContacts()

        val ContactsAdapter = ContactsAdapter(contactList)


        val inviteRecyclerview = requireView().findViewById<RecyclerView>(R.id.recyclerView_invite)
        inviteRecyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,  false)
        inviteRecyclerview.adapter = ContactsAdapter

    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }

//    @SuppressLint("Range")
    private fun fetchContacts(): ArrayList<Contacts> {
        val contentResolver = requireActivity().contentResolver
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        val contactList : ArrayList<Contacts> = ArrayList()

        while ( cursor != null && cursor.moveToNext()) {
            val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            val hasPhoneNum = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

            if(hasPhoneNum > 0) {
                val phoneCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    arrayOf(id),
                    null
                )

                while (phoneCursor != null && phoneCursor.moveToNext()) {

                    val num =
                        phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val name =
                        phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

                    contactList.add(Contacts(name, num))
                }
                if (phoneCursor != null) phoneCursor.close()

            }
        }
    if (cursor != null) cursor.close()

    return contactList
    }
}
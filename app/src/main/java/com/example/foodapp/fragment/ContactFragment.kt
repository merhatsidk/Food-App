package com.example.foodapp.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R

class ContactFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_contact, container, false)

        val btnPhoneCall = rootView.findViewById<View>(R.id.btnPhoneCall)
        val btnSendEmail = rootView.findViewById<View>(R.id.btnSendEmail)

        btnPhoneCall.setOnClickListener {
            initiatePhoneCall()
        }

        btnSendEmail.setOnClickListener {
            initiateEmail()
        }

        return rootView
    }

    private fun initiatePhoneCall() {
        val phoneNumber = "+2512345678"
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    private fun initiateEmail() {
        val email = "naol@naol.com"
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$email")
        startActivity(intent)
//        val subject = "Food review"
//        val message = "Hello, I would like to ..."
//        val intent = Intent(Intent.ACTION_SEND).apply {
//            type = "message/rfc822"
//            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
//            putExtra(Intent.EXTRA_SUBJECT, subject)
//            putExtra(Intent.EXTRA_TEXT, message)
//        }
//        if (intent.resolveActivity(requireActivity().packageManager) != null) {
//            startActivity(Intent.createChooser(intent, "Send Email"))
//        }
    }

}
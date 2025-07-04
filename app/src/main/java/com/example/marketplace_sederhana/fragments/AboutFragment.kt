package com.example.marketplace_sederhana.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.marketplace_sederhana.R

class AboutFragment : Fragment() {

    private lateinit var aboutTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        aboutTextView = view.findViewById(R.id.about_text_view)
        aboutTextView.text = "This app was developed by: \nJohn, Jane, and Jack."

        return view
    }
}

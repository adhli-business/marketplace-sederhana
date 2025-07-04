package com.example.marketplace_sederhana.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.marketplace_sederhana.R
import com.example.marketplace_sederhana.fragments.ProductListFragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            // Simulate successful login and navigate to ProductListFragment
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, ProductListFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        return view
    }
}

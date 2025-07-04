package com.example.marketplace_sederhana.fragments.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace_sederhana.MainActivity
import com.example.marketplace_sederhana.R
import com.example.marketplace_sederhana.fragments.productlist.ProductListFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        etUsername = view.findViewById(R.id.et_username)
        etPassword = view.findViewById(R.id.et_password)
        btnLogin = view.findViewById(R.id.btn_login)
        val tvRegisterPrompt = view.findViewById<View>(R.id.tv_register_prompt)

        btnLogin.setOnClickListener {
            handleLogin()
        }

        tvRegisterPrompt.setOnClickListener {
            showRegisterDialog()
        }
    }

    private fun handleLogin() {
        val username = etUsername.text?.toString()?.trim() ?: ""
        val password = etPassword.text?.toString()?.trim() ?: ""

        when {
            username.isEmpty() -> showToast("Username tidak boleh kosong")
            password.isEmpty() -> showToast("Password tidak boleh kosong")
            !viewModel.isUserRegistered(username) -> showToast("Username belum terdaftar")
            !viewModel.login(username, password) -> showToast("Password salah")
            else -> {
                showToast("Login berhasil")
                (activity as MainActivity).loadFragment(ProductListFragment())
            }
        }
    }

    private fun showRegisterDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_register, null)
        val etRegUsername = dialogView.findViewById<TextInputEditText>(R.id.et_reg_username)
        val etRegPassword = dialogView.findViewById<TextInputEditText>(R.id.et_reg_password)

        AlertDialog.Builder(requireContext())
            .setTitle("Daftar Akun Baru")
            .setView(dialogView)
            .setPositiveButton("Daftar") { dialog, _ ->
                val username = etRegUsername.text?.toString()?.trim() ?: ""
                val password = etRegPassword.text?.toString()?.trim() ?: ""

                when {
                    username.isEmpty() -> showToast("Username tidak boleh kosong")
                    password.isEmpty() -> showToast("Password tidak boleh kosong")
                    viewModel.isUserRegistered(username) -> showToast("Username sudah terdaftar")
                    else -> {
                        if (viewModel.register(username, password)) {
                            showToast("Registrasi berhasil! Silakan login")
                            dialog.dismiss()
                            // Auto-fill the login form
                            etUsername.setText(username)
                            etPassword.setText(password)
                        }
                    }
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
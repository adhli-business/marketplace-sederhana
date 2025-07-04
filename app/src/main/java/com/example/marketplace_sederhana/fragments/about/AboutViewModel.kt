package com.example.marketplace_sederhana.fragments.about


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {
    private val _teamInfo = MutableLiveData<String>()
    private val _appInfo = MutableLiveData<String>()

    init {
        loadData()
    }

    private fun loadData() {
        _teamInfo.value = """
            Tim Pengembang:
            • John Doe - Project Manager
            • Jane Smith - Android Developer
            • Bob Johnson - UI/UX Designer
            • Alice Brown - Quality Assurance
        """.trimIndent()

        _appInfo.value = """
            Marketplace App v1.0
            
            Aplikasi marketplace sederhana yang dibuat untuk keperluan pembelajaran Android Development.
            
            Fitur:
            • Login sederhana
            • Daftar produk
            • Detail produk
            • Keranjang belanja
            • Informasi tim
        """.trimIndent()
    }

    fun getTeamInfo(): LiveData<String> {
        return _teamInfo
    }

    fun getAppInfo(): LiveData<String> {
        return _appInfo
    }
}
package com.example.marketplace_sederhana.fragments.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace_sederhana.R

class AboutFragment : Fragment() {
    private lateinit var viewModel: AboutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AboutViewModel::class.java]

        val tvTeamInfo: TextView = view.findViewById(R.id.tv_team_info)
        val tvAppInfo: TextView = view.findViewById(R.id.tv_app_info)

        viewModel.getTeamInfo().observe(viewLifecycleOwner) { teamInfo ->
            tvTeamInfo.text = teamInfo
        }

        viewModel.getAppInfo().observe(viewLifecycleOwner) { appInfo ->
            tvAppInfo.text = appInfo
        }
    }
}
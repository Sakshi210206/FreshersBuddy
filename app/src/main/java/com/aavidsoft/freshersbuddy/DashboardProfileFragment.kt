package com.aavidsoft.freshersbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class DashboardProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dashboard_profile, container, false)

        // Find views
        val backArrow = view.findViewById<ImageView>(R.id.back_arrow)

        // Handle back button click
        backArrow.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}

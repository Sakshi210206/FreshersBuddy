package com.aavidsoft.freshersbuddy

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class DashboardProfileFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dashboard_profile, container, false)
        val backArrow = view.findViewById<ImageView>(R.id.back_arrow)
        backArrow.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }
}

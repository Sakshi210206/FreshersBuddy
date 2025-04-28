package com.aavidsoft.freshersbuddy

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.aavidsoft.freshersbuddy.articles.allarticles.fragments.ArticlesFragment
import com.aavidsoft.freshersbuddy.articles.more.fragment.MoreFragment
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.fragments.InterviewQuestionFragment
import com.aavidsoft.freshersbuddy.tutorials.fragments.TutorialsCategoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dashboard_fragment, container, false)

        val articlesCard = view.findViewById<CardView>(R.id.Articles)
        val interviewCard = view.findViewById<CardView>(R.id.InterviewQuestions)
        val tutorialsCard = view.findViewById<CardView>(R.id.Tutorials)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

       val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.dashboardToolbar)
        toolbar.title = "Fresher Buddy"

        articlesCard.setOnClickListener {
            replaceFragment(ArticlesFragment())
        }

        interviewCard.setOnClickListener {
            replaceFragment(InterviewQuestionFragment())
        }
        tutorialsCard.setOnClickListener {
            val bottomSheet = TutorialsCategoryFragment()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }



        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(DashboardFragment())
                    true
                }
                R.id.more -> {
                    replaceFragment(MoreFragment())
                    true
                }
                R.id.profile -> {
                    replaceFragment(DashboardProfileFragment())
                    true
                }
                else -> false
            }
        }
        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .addToBackStack(null)
            .commit()
    }
}

package com.aavidsoft.freshersbuddy.tutorials.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aavidsoft.freshersbuddy.databinding.TutorialDetailsFragmentBinding
import com.aavidsoft.freshersbuddy.tutorials.models.TutorialsDetail
import com.aavidsoft.freshersbuddy.tutorials.network.TutorialsApiService
import com.aavidsoft.freshersbuddy.tutorials.repository.TutorialsDetailsRepository
import com.aavidsoft.freshersbuddy.tutorials.viewmodel.TutorialsDetailsViewModel
import com.aavidsoft.freshersbuddy.utils.factory.ViewModelFactory

class TutorialsDetailsFragment : Fragment() {
    private lateinit var tutorialsDetailsViewModel: TutorialsDetailsViewModel
    private lateinit var tutorialsDetailsFragment: TutorialDetailsFragmentBinding
    private var tutorialId : Int = 0
    private lateinit var tutorialDetails: TutorialsDetail

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        initObserver()
         tutorialsDetailsFragment = TutorialDetailsFragmentBinding.inflate(layoutInflater)
        tutorialsDetailsFragment.root.setOnClickListener{}
        if(arguments!= null){
           tutorialId = arguments?.getInt("tutorialId") ?: 0
        }

        tutorialsDetailsViewModel.fetchTutorialDetails(tutorialId)
        return tutorialsDetailsFragment.root
    }
    private fun initViewModel(){
        tutorialsDetailsViewModel = ViewModelProvider(
            this,ViewModelFactory(
                TutorialsDetailsRepository(
                    TutorialsApiService.getInstance()
                )
            )
        )[TutorialsDetailsViewModel::class.java]
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun initObserver() {
        tutorialsDetailsViewModel.tutorialDetailStatusMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            tutorialsDetailsFragment.tutorialDetailsItem = it
            tutorialDetails = it

            val htmlContent = """
            <html>
                <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        body {
                            font-family: sans-serif;
                            padding: 16px;
                            color: #000;
                            line-height: 1.6;
                        }
                        h1 {
                            font-size: 6vw;
                            font-weight: bold;
                            margin-bottom: 16px;
                        }
                        p {
                            font-size: 4.2vw;
                            margin: 8px 0;
                        }
                        .meta {
                            font-size: 3.5vw;
                            color: #666;
                            margin-top: 12px;
                        }
                        img {
                            width: 100%;
                            height: auto;
                            margin-bottom: 20px;
                        }
                    </style>
                </head>
                <body>
                    <img src="${it.imageUrls?.getOrNull(0) ?: ""}" alt="Article Image"/>
                    <h1>${it.title}</h1>
                    ${it.body}
                    <p class="meta">Author: ${it.author}</p>
                    <p class="meta">Published on: ${it.publishedOn}</p>
                </body>
            </html>
        """.trimIndent()

            tutorialsDetailsFragment.webView.settings.javaScriptEnabled = true
            tutorialsDetailsFragment.webView.loadDataWithBaseURL(
                null,
                 htmlContent,
                "text/html",
                "utf-8",
                null
            )
        }
    }
}
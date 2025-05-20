package com.aavidsoft.freshersbuddy.tutorials.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aavidsoft.freshersbuddy.tutorials.models.TutorialsDetail
import com.aavidsoft.freshersbuddy.tutorials.repository.TutorialsDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TutorialsDetailsViewModel(
    private val  tutorialDetailsRepository : TutorialsDetailsRepository
) : ViewModel() {

    val tutorialDetailStatusMutableLiveData = MutableLiveData<TutorialsDetail>()
    fun fetchTutorialDetails(id : Int){
        CoroutineScope(Dispatchers.IO).launch {
            val tutorialsDetail = tutorialDetailsRepository.fetchTutorialsDetails(id)
            withContext(Dispatchers.Main){
                tutorialDetailStatusMutableLiveData.postValue(tutorialsDetail)
            }
        }
    }
}
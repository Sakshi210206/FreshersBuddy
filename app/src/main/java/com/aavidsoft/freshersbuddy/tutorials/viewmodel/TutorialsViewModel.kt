package com.aavidsoft.freshersbuddy.tutorials.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aavidsoft.freshersbuddy.tutorials.models.ItemsTutorial
import com.aavidsoft.freshersbuddy.tutorials.repository.TutorialsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TutorialsViewModel (
    private val tutorialsRepository: TutorialsRepository
):ViewModel(){
    val tutorialsUpdateAvailableLiveData = MutableLiveData<Boolean>()
    val tutorials = ArrayList<ItemsTutorial>()
    var hasMoreData = true
    var isFetchingTutorialCategory = false

    fun fetchTutorialCategory(id: Int){
        if (!hasMoreData){
            tutorialsUpdateAvailableLiveData.postValue(false)
            return
        }
        if(isFetchingTutorialCategory){
            return
        }
        isFetchingTutorialCategory = true

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val categoryTutorial = tutorialsRepository.fetchTutorialsList(id)
                if(categoryTutorial != null){
                    withContext(Dispatchers.Main){
                        this@TutorialsViewModel.tutorials.addAll(categoryTutorial)
                        tutorialsUpdateAvailableLiveData.postValue(true)
                        hasMoreData = false
                    }
                }else{
                    tutorialsUpdateAvailableLiveData.postValue(false)
                }
            }catch ( e: Exception){
                tutorialsUpdateAvailableLiveData.postValue(false)
            }finally {
                isFetchingTutorialCategory = false
            }
        }
    }
}
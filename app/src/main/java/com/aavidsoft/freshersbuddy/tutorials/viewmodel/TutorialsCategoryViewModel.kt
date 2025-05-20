package com.aavidsoft.freshersbuddy.tutorials.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aavidsoft.freshersbuddy.tutorials.models.ItemCategory
import com.aavidsoft.freshersbuddy.tutorials.repository.TutorialsCategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TutorialsCategoryViewModel(
    private val tutorialsCategoryRepository: TutorialsCategoryRepository
):ViewModel() {
    val tutorialCategoryUpdateAvailableLiveData = MutableLiveData<Boolean>()
    val tutorialCategory = ArrayList<ItemCategory>()
    var hasMoreData = true
    var isFetchingTutorialCategory = false

    fun fetchTutorialCategory() {
        if (!hasMoreData) {
            tutorialCategoryUpdateAvailableLiveData.postValue(false)
            return
        }
        if (isFetchingTutorialCategory) {
            return
        }
        isFetchingTutorialCategory = true

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val categoryTutorial = tutorialsCategoryRepository.fetchTutorialCategoryList()
                if (categoryTutorial != null) {
                    withContext(Dispatchers.Main) {
                        this@TutorialsCategoryViewModel.tutorialCategory.addAll(categoryTutorial)
                        tutorialCategoryUpdateAvailableLiveData.postValue(true)
                        hasMoreData = false
                    }
                } else {
                    tutorialCategoryUpdateAvailableLiveData.postValue(false)
                }
            } catch (e: Exception) {
                tutorialCategoryUpdateAvailableLiveData.postValue(false)
            } finally {
                isFetchingTutorialCategory = false
            }
        }
    }
}
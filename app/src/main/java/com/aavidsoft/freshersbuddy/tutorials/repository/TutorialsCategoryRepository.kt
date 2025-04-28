package com.aavidsoft.freshersbuddy.tutorials.repository

import com.aavidsoft.freshersbuddy.tutorials.models.ItemCategory
import com.aavidsoft.freshersbuddy.tutorials.network.TutorialsApiService
import com.aavidsoft.freshersbuddy.utils.repository.Repository

class TutorialsCategoryRepository(private val tutorialsApiService : TutorialsApiService
): Repository() {
    suspend fun fetchTutorialCategoryList() : ArrayList<ItemCategory>?{
        return tutorialsApiService.fetchTutorialsCategory().data
    }
}
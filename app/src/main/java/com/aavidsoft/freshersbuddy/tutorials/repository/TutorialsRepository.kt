package com.aavidsoft.freshersbuddy.tutorials.repository

import com.aavidsoft.freshersbuddy.tutorials.models.ItemsTutorial
import com.aavidsoft.freshersbuddy.tutorials.network.TutorialsApiService
import com.aavidsoft.freshersbuddy.utils.repository.Repository

class TutorialsRepository(
    private val tutorialsApiService: TutorialsApiService
):Repository() {
    suspend fun fetchTutorialsList(): ArrayList<ItemsTutorial>?{
        return tutorialsApiService.fetchTutorials().data
    }
}
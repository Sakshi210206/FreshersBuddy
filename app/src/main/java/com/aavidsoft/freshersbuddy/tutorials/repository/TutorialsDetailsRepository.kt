package com.aavidsoft.freshersbuddy.tutorials.repository

import com.aavidsoft.freshersbuddy.tutorials.models.TutorialsDetail
import com.aavidsoft.freshersbuddy.tutorials.network.TutorialsApiService
import com.aavidsoft.freshersbuddy.utils.repository.Repository

class TutorialsDetailsRepository(
    private val tutorialsApiService: TutorialsApiService
) : Repository(){
    suspend fun fetchTutorialsDetails(id : Int) : TutorialsDetail {
        val response = tutorialsApiService.fetchTutorialsDetails(id)
        return requireNotNull(response.data) {
            "Tutorial Detail response is null"
        }
    }
}
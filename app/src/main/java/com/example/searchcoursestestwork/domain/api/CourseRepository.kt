package com.example.searchcoursestestwork.domain.api

import com.example.searchcoursestestwork.domain.models.Course

interface CourseRepository {
    var hasNext: Boolean
    var hasPrevious: Boolean
    var pageSize: Int
    suspend fun getNext(): List<Course>
    suspend fun getPrevious(): List<Course>
    fun clearPagination()
}
package com.example.searchcoursestestwork.data.dto

import com.example.searchcoursestestwork.data.network.SearchCoursesApi
import com.example.searchcoursestestwork.domain.api.CourseRepository
import com.example.searchcoursestestwork.domain.models.Course

class CourseRepositoryImpl(private val api: SearchCoursesApi) : CourseRepository {
    private var page: Long = 0
    override var pageSize: Int = 20
    override var hasNext: Boolean = false
    override var hasPrevious: Boolean = false
    override suspend fun getNext(): List<Course> {
        try {
            val response = api.fetchCourses(++page, pageSize)
            hasNext = response.meta.hasNext
            var courses = response.courses

            if (courses.isEmpty() && hasNext) {
                courses = getNext()
            }
            return courses
        } catch (err: Exception) {
            return emptyList()
        }
    }
    override suspend fun getPrevious(): List<Course> {
        try {
            val response = api.fetchCourses(--page, pageSize)
            hasPrevious = response.meta.hasPrevious && page > 1
            var courses = response.courses

            if (courses.isEmpty() && hasPrevious) {
                courses = getPrevious()
            }
            return courses
        } catch (err: Exception) {
            return emptyList()
        }
    }
    override fun clearPagination() {
        page = 0
        hasNext = false
        hasPrevious = false
    }
}
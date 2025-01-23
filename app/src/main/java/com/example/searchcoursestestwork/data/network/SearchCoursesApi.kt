package com.example.searchcoursestestwork.data.network

import com.example.searchcoursestestwork.domain.models.Course
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCoursesApi {
    @GET("courses")
    suspend fun fetchCourses(
        @Query("page") page: Long,
        @Query("page_size") pageSize: Int,
    ): CourseResponse
    companion object {
        const val BASE_URL = "https://stepik.org/api/"
    }
}
data class CourseResponse(val courses: List<Course>, val meta: Meta)
data class Meta(val page: Long, val hasNext: Boolean, val hasPrevious: Boolean)
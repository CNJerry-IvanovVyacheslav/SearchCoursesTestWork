package com.example.searchcoursestestwork.di

import com.example.searchcoursestestwork.data.dto.CourseRepositoryImpl
import com.example.searchcoursestestwork.data.network.SearchCoursesApi
import com.example.searchcoursestestwork.domain.api.CourseRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(SearchCoursesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }).build()
            ).build()
            .create(SearchCoursesApi::class.java)
    }

    single<CourseRepository> {
        CourseRepositoryImpl(get())
    }
}
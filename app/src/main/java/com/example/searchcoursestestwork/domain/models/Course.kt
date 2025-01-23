package com.example.searchcoursestestwork.domain.models

data class Course(
    val id: Long,
    val title: String,
    val summary: String,
    val cover: String,
    val isPaid: Boolean,
    val displayPrice: String,
    val createDate: String
)

package com.course.ex1

//@Serializable
data class Company(
    val id:Int,
    val name: String,
    val fieldOfActivity: String,
    val vacancies: List<String> = listOf(),
    val contacts: String
)

package com.course.domain.model

data class Company(
    val id:Int,
    val name: String,
    val fieldOfActivity: String,
  //  val vacancies: List<Vacancy>,
    val contacts: String
)

data class Vacancy(
    val id: Int,
    val profession: String,
    val level: String,
    val salary: Int,
    val description: String
)
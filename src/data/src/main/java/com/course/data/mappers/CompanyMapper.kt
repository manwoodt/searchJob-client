package com.course.data.mappers

import com.course.data.model.CompanyDto
import com.course.domain.model.Company

fun CompanyDto.toDomainModel():Company{
    return Company(
        companyId = this.companyId,
        name = this.name,
        fieldOfActivity = this.fieldOfActivity
    )
}

package com.ds3c.apitcc.service

import com.ds3c.apitcc.dto.SchoolClassDTO
import com.ds3c.apitcc.mapper.SchoolClassMapper
import com.ds3c.apitcc.model.SchoolClass
import com.ds3c.apitcc.repository.SchoolClassRepository
import org.springframework.stereotype.Service

@Service
class SchoolClassService(
    val schoolClassRepository: SchoolClassRepository,
    val schoolClassMapper: SchoolClassMapper
    ) {
    fun listSchoolClasses() : List<SchoolClass> {
        return schoolClassRepository.findAll()
    }

    fun getSchoolClass(id: Long) : SchoolClass {
        return schoolClassRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding class with ID $id") }
    }

    fun createSchoolClass(dto: SchoolClassDTO) : SchoolClass {
        val schoolClass = schoolClassMapper.toModel(dto)
        return schoolClassRepository.save(schoolClass)
    }

    fun updateSchoolClass(id: Long, dto: SchoolClassDTO) : SchoolClass {
        val schoolClass = schoolClassRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding class with ID $id") }
        schoolClassMapper.updateModelFromDTO(dto, schoolClass)
        return schoolClassRepository.save(schoolClass)
    }

    fun deleteSchoolClass(id: Long) : String {
        schoolClassRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding class with ID $id") }
        schoolClassRepository.deleteById(id)
        return "Class with ID $id was deleted."
    }
}
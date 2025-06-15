package com.ds3c.apitcc.service

import com.ds3c.apitcc.dto.StudentDTO
import com.ds3c.apitcc.mapper.StudentMapper
import com.ds3c.apitcc.model.Student
import com.ds3c.apitcc.repository.SchoolClassRepository
import com.ds3c.apitcc.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(
    val studentRepository: StudentRepository,
    val schoolClassRepository: SchoolClassRepository,
    val studentMapper: StudentMapper
) {
    fun listStudents() : List<Student> {
        return studentRepository.findAll()
    }

    fun getStudentById(id: Long) : Student {
        return studentRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding student with ID $id") }
    }

    fun getStudentByFilter(field: String, value: String) : List<Student> {
        val validFields = listOf("name", "rm", "ra", "cpf", "email", "phone", "class")
        require(field in validFields) { "Invalid field: $field." }
        if(field == "class") {
            val schoolclass = schoolClassRepository
                .findById(value.toLong())
                .orElseThrow { RuntimeException("Error finding class with ID $value.") }
            return studentRepository.findByFilter("schoolclass", schoolclass)
        }
        return studentRepository.findByFilter(field, value)
    }

    fun createStudent(dto: StudentDTO) : Student {
        if(dto.schoolclassId == null) {
            throw RuntimeException("The class cannot be empty.")
        }
        val schoolClass = schoolClassRepository
            .findById(dto.schoolclassId)
            .orElseThrow { RuntimeException("Error finding class with ID ${dto.schoolclassId}.") }
        val student = studentMapper.toModel(dto)
        student.schoolclass = schoolClass
        return studentRepository.save(student)
    }

    fun updateStudent(id: Long, dto: StudentDTO) : Student {
        if(dto.schoolclassId != null) {
            val schoolClass = schoolClassRepository
                .findById(dto.schoolclassId)
                .orElseThrow { RuntimeException("Error finding class with ID ${dto.schoolclassId}.") }
            val student = studentRepository
                .findById(id)
                .orElseThrow { RuntimeException("Error finding student with ID $id.") }
            studentMapper.updateModelFromDTO(dto, student)
            student.schoolclass = schoolClass
            return studentRepository.save(student)
        }
        val student = studentRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding student with ID $id.") }
        studentMapper.updateModelFromDTO(dto, student)
        return studentRepository.save(student)
    }

    fun deleteStudent(id: Long) : String {
        studentRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding student with ID $id") }
        studentRepository.deleteById(id)
        return "Student with ID $id was deleted."
    }
}
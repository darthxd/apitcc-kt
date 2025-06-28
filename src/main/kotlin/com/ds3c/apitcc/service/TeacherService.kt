package com.ds3c.apitcc.service

import com.ds3c.apitcc.dto.TeacherDTO
import com.ds3c.apitcc.mapper.TeacherMapper
import com.ds3c.apitcc.model.Teacher
import com.ds3c.apitcc.repository.TeacherRepository
import org.springframework.stereotype.Service

@Service
class TeacherService(
    val teacherRepository: TeacherRepository,
    val teacherMapper: TeacherMapper
) {
    fun listTeachers() : List<Teacher> {
        return teacherRepository.findAll()
    }
    fun getTeacherById(id: Long) : Teacher {
        return teacherRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding teacher with ID $id") }
    }
    fun getTeacherByUsername(username: String) : Teacher {
        return teacherRepository
            .findByUsername(username)
    }
    fun createTeacher(dto: TeacherDTO) : Teacher {
        val teacher = teacherMapper.toModel(dto)
        return teacherRepository.save(teacher)
    }
    fun updateTeacher(id:Long, dto: TeacherDTO) : Teacher {
        val teacher = teacherRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding teacher with ID $id") }
        teacherMapper.updateModelFromDTO(dto, teacher)
        return teacherRepository.save(teacher)
    }
    fun deleteTeacher(id: Long) : String {
        teacherRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding teacher with ID $id") }
        teacherRepository.deleteById(id)
        return "Teacher with ID $id was deleted."
    }
}

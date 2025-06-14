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
            .orElseThrow { RuntimeException("Erro ao buscar aluno.") }
    }
    fun getStudentByFilter(field: String, value: String) : List<Student> {
        if(field == "id") {
            return studentRepository.findByFilter(field, value.toLong())
        }
        return studentRepository.findByFilter(field, value)
    }
    fun createStudent(dto: StudentDTO) : Student {
        if(dto.schoolclassId == null) {
            throw RuntimeException("A classe não pode ser nula.")
        }
        val schoolClass = schoolClassRepository
            .findById(dto.schoolclassId)
            .orElseThrow { RuntimeException("Classe não encontrada.") }
        val student = studentMapper.toModel(dto)
        student.schoolclass = schoolClass
        return studentRepository.save(student)
    }
    fun updateStudent(id: Long, dto: StudentDTO) : Student {
        if(dto.schoolclassId != null) {
            val schoolClass = schoolClassRepository
                .findById(dto.schoolclassId)
                .orElseThrow { RuntimeException("Classe não encontrada.") }
            val student = studentRepository
                .findById(id)
                .orElseThrow { RuntimeException("Erro ao buscar aluno.") }
            studentMapper.updateModelFromDTO(dto, student)
            student.schoolclass = schoolClass
            return studentRepository.save(student)
        }
        val student = studentRepository
            .findById(id)
            .orElseThrow { RuntimeException("Erro ao buscar aluno.") }
        studentMapper.updateModelFromDTO(dto, student)
        return studentRepository.save(student)
    }
    fun deleteStudent(id: Long) : String {
        val student = studentRepository
            .findById(id)
            .orElseThrow { RuntimeException("Erro ao buscar aluno.") }
        studentRepository.deleteById(id)
        return "Aluno ${student.name} deletado com sucesso."
    }
}
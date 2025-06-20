package com.ds3c.apitcc.controller

import com.ds3c.apitcc.dto.StudentDTO
import com.ds3c.apitcc.model.Student
import com.ds3c.apitcc.service.StudentService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/student")
class StudentController(
    val studentService: StudentService
) {
    @GetMapping
    fun listStudents() : List<Student> {
        return studentService.listStudents()
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable("id") id: Long) : Student {
        return studentService.getStudentById(id)
    }

    @GetMapping("/{field}/{value}")
    fun getStudentByFilter(
        @PathVariable("field") field: String,
        @PathVariable("value") value: String
    ) : List<Student> {
        return studentService.getStudentByFilter(field, value)
    }

    @PostMapping
    fun createStudent(@RequestBody @Validated dto: StudentDTO) : Student {
        return studentService.createStudent(dto)
    }

    @PutMapping("/{id}")
    fun updateStudent(
        @PathVariable("id") id: Long,
        @RequestBody @Validated dto: StudentDTO
    ) : Student {
        return studentService.updateStudent(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable("id") id: Long) : String {
        return studentService.deleteStudent(id)
    }
}
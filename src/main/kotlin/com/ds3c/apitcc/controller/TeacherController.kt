package com.ds3c.apitcc.controller

import com.ds3c.apitcc.dto.TeacherDTO
import com.ds3c.apitcc.model.Teacher
import com.ds3c.apitcc.service.TeacherService
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
@RequestMapping("/api/teacher")
class TeacherController(
    val teacherService: TeacherService,
) {
    @GetMapping
    fun listTeachers() : List<Teacher> {
        return teacherService.listTeachers()
    }

    @GetMapping("/{id}")
    fun getTeacher(@PathVariable("id") id: Long) : Teacher {
        return teacherService.getTeacherById(id)
    }

    @PostMapping
    fun createTeacher(@RequestBody dto: TeacherDTO) : Teacher {
        return teacherService.createTeacher(dto)
    }

    @PutMapping("/{id}")
    fun updateTeacher(
        @PathVariable("id") id: Long,
        @RequestBody @Validated dto: TeacherDTO
    ) : Teacher {
        return teacherService.updateTeacher(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteTeacher(@PathVariable("id") id: Long) : String {
        return teacherService.deleteTeacher(id)
    }
}

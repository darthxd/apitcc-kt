package com.ds3c.apitcc.controller

import com.ds3c.apitcc.dto.SchoolClassDTO
import com.ds3c.apitcc.model.SchoolClass
import com.ds3c.apitcc.service.SchoolClassService
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
@RequestMapping("/api/class")
class SchoolClassController(
    val schoolClassService: SchoolClassService
) {
    @GetMapping
    fun listSchoolClasses() : List<SchoolClass> {
        return schoolClassService.listSchoolClasses()
    }
    @GetMapping("/{id}")
    fun getSchoolClass(@PathVariable("id") id: Long) : SchoolClass {
        return schoolClassService.getSchoolClass(id)
    }
    @PostMapping
    fun createSchoolClass(@RequestBody @Validated schoolClassDTO: SchoolClassDTO) : SchoolClass {
        return schoolClassService.createSchoolClass(schoolClassDTO)
    }
    @PutMapping("/{id}")
    fun updateSchoolClass(
        @PathVariable("id") id: Long,
        @RequestBody @Validated schoolClassDTO: SchoolClassDTO
    ) : SchoolClass {
        return schoolClassService.updateSchoolClass(id, schoolClassDTO)
    }
    @DeleteMapping("/{id}")
    fun deleteSchoolClass(@PathVariable("id") id: Long) : String {
        return schoolClassService.deleteSchoolClass(id)
    }
}
package com.ds3c.apitcc.controller

import com.ds3c.apitcc.dto.AdminDTO
import com.ds3c.apitcc.model.Admin
import com.ds3c.apitcc.service.AdminService
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
@RequestMapping("/api/admin")
class AdminController(
    val adminService: AdminService,
) {
    @GetMapping
    fun listAdmins() : List<Admin> {
        return adminService.listAdmins()
    }

    @GetMapping("/{id}")
    fun getAdmin(@PathVariable("id") id: Long) : Admin {
        return adminService.getAdmin(id)
    }

    @PostMapping
    fun createAdmin(@RequestBody dto: AdminDTO) : Admin {
        return adminService.createAdmin(dto)
    }

    @PutMapping("/{id}")
    fun updateAdmin(
        @PathVariable("id") id: Long,
        @RequestBody @Validated dto: AdminDTO
    ) : Admin {
        return adminService.updateAdmin(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteAdmin(@PathVariable("id") id: Long) : String {
        return adminService.deleteAdmin(id)
    }
}
package com.ds3c.apitcc.service

import com.ds3c.apitcc.dto.AdminDTO
import com.ds3c.apitcc.mapper.AdminMapper
import com.ds3c.apitcc.model.Admin
import com.ds3c.apitcc.repository.AdminRepository
import org.springframework.stereotype.Service

@Service
class AdminService(
    val adminRepository: AdminRepository,
    val adminMapper: AdminMapper
) {
    fun listAdmins() : List<Admin> {
        return adminRepository.findAll()
    }
    fun getAdmin(id: Long) : Admin {
        return adminRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding admin with ID $id") }
    }
    fun createAdmin(dto: AdminDTO) : Admin {
        val admin = adminMapper.toModel(dto)
        return adminRepository.save(admin)
    }
    fun updateAdmin(id:Long, dto: AdminDTO) : Admin {
        val admin = adminRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding admin with ID $id") }
        adminMapper.updateModelFromDTO(dto, admin)
        return adminRepository.save(admin)
    }
    fun deleteAdmin(id: Long) : String {
        adminRepository
            .findById(id)
            .orElseThrow { RuntimeException("Error finding admin with ID $id") }
        adminRepository.deleteById(id)
        return "Admin with ID $id was deleted."
    }
}
package com.ds3c.apitcc.repository

import com.ds3c.apitcc.model.Admin
import com.ds3c.apitcc.model.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AdminRepository : JpaRepository<Admin, Long>{
    fun findByUsername(login: String) : Admin
}
package com.ds3c.apitcc.service

import com.ds3c.apitcc.repository.AdminRepository
import com.ds3c.apitcc.repository.StudentRepository
import com.ds3c.apitcc.repository.TeacherRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    val adminRepository: AdminRepository,
    val studentRepository: StudentRepository,
    val teacherRepository: TeacherRepository,
    val jwtService: JwtService
) {
    fun adminLogin(username: String, password: String) : String {
        val admin = adminRepository
            .findByUsername(username)
        if(password != admin.password) {
            throw BadCredentialsException("The password for admin $username is incorrect.")
        }
        return jwtService.generateToken(admin)
    }

    fun studentLogin(username: String, password: String) : String {
        val student = studentRepository
            .findByUsername(username)
        if(password != student.password) {
            throw BadCredentialsException("The password for student $username is incorrect.")
        }
        return jwtService.generateToken(student)
    }

    fun teacherLogin(username: String, password: String) : String {
        val teacher = teacherRepository
            .findByUsername(username)
        if(password != teacher.password) {
            throw BadCredentialsException("The password for teacher $username is incorrect.")
        }
        return jwtService.generateToken(teacher)
    }
}
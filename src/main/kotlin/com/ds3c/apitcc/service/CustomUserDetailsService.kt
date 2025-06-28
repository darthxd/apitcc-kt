package com.ds3c.apitcc.service

import com.ds3c.apitcc.model.User
import com.ds3c.apitcc.repository.AdminRepository
import com.ds3c.apitcc.repository.StudentRepository
import com.ds3c.apitcc.repository.TeacherRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    val adminRepository: AdminRepository,
    val studentRepository: StudentRepository,
    val teacherRepository: TeacherRepository
) : UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = adminRepository.findByUsername(username)
            ?: studentRepository.findByUsername(username)
            ?: teacherRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("Error finding user $username")

        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            listOf(SimpleGrantedAuthority(user.role.name))
        )
    }
}
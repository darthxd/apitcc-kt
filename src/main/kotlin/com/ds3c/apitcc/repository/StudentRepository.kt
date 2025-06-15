package com.ds3c.apitcc.repository

import com.ds3c.apitcc.model.Student
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long>, StudentCustomRepository {}

interface StudentCustomRepository {
    fun findByFilter(field: String, value: Any) : List<Student>
}

@Repository
class StudentRepositoryImpl(
    val entityManager: EntityManager
) : StudentCustomRepository {
    override fun findByFilter(field: String, value: Any): List<Student> {
        val jpql = if (value is String) {
            "SELECT s FROM Student s WHERE LOWER(s.$field) LIKE LOWER (CONCAT('%', :value, '%'))"
        } else {
            "SELECT s FROM Student s WHERE $field = :value"
        }
        val query = entityManager.createQuery(jpql, Student::class.java)
            .setParameter("value", value)
        return query.resultList
    }
}
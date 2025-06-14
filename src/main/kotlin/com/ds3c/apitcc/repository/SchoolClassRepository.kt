package com.ds3c.apitcc.repository

import com.ds3c.apitcc.model.SchoolClass
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SchoolClassRepository : JpaRepository<SchoolClass, Long> {
}
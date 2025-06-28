package com.ds3c.apitcc.model

import com.ds3c.apitcc.enums.RolesEnum

interface User {
    val username: String
    val password: String
    val role: RolesEnum
}
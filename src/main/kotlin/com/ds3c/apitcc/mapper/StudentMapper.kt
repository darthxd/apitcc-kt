package com.ds3c.apitcc.mapper

import com.ds3c.apitcc.dto.StudentDTO
import com.ds3c.apitcc.model.Student
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface StudentMapper {
    fun toModel(dto: StudentDTO) : Student
    fun toDTO(model: Student) : StudentDTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateModelFromDTO(dto: StudentDTO, @MappingTarget model: Student)
}
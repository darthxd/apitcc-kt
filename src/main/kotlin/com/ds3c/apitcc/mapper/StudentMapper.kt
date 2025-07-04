package com.ds3c.apitcc.mapper

import com.ds3c.apitcc.dto.StudentDTO
import com.ds3c.apitcc.model.Student
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface StudentMapper {
    @Mapping(target = "role", expression = "java(com.ds3c.apitcc.enums.RolesEnum.STUDENT)")
    fun toModel(dto: StudentDTO) : Student
    fun toDTO(model: Student) : StudentDTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateModelFromDTO(dto: StudentDTO, @MappingTarget model: Student)
}
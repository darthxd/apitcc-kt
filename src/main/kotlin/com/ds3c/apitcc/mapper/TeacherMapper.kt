package com.ds3c.apitcc.mapper

import com.ds3c.apitcc.dto.TeacherDTO
import com.ds3c.apitcc.model.Teacher
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface TeacherMapper {
    fun toModel(dto: TeacherDTO) : Teacher
    fun toDTO(model: Teacher): TeacherDTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateModelFromDTO(dto: TeacherDTO, @MappingTarget model: Teacher)
}

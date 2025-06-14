package com.ds3c.apitcc.mapper

import com.ds3c.apitcc.dto.SchoolClassDTO
import com.ds3c.apitcc.model.SchoolClass
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface SchoolClassMapper {
    fun toModel(dto: SchoolClassDTO): SchoolClass
    fun toDTO(model: SchoolClass): SchoolClassDTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateModelFromDTO(dto: SchoolClassDTO, @MappingTarget model: SchoolClass)
}
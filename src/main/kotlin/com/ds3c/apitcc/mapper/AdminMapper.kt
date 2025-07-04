package com.ds3c.apitcc.mapper

import com.ds3c.apitcc.dto.AdminDTO
import com.ds3c.apitcc.model.Admin
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface AdminMapper {
    @Mapping(target = "role", expression = "java(com.ds3c.apitcc.enums.RolesEnum.ADMIN)")
    fun toModel(dto: AdminDTO) : Admin
    fun toDTO(model: Admin): AdminDTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateModelFromDTO(dto: AdminDTO, @MappingTarget model: Admin)
}
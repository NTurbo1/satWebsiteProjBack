package com.nturbo1.satWebsiteProjBack.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.Role;
import com.nturbo1.satWebsiteProjBack.service.dto.request.RoleRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.RoleResponseDto;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	
	List<RoleResponseDto> mapToRoleResponseDtoList(List<Role> entityList);
	
	List<Role> mapToRoleList(List<RoleRequestDto> dtoRequestList);
	
	RoleResponseDto map(Role entity);
	
	Role map(RoleRequestDto value);
}

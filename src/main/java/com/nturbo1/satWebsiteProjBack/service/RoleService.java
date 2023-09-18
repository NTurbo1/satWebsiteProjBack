package com.nturbo1.satWebsiteProjBack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.RoleRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.Role;
import com.nturbo1.satWebsiteProjBack.service.dto.request.RoleRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.RoleResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.RoleMapper;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	private final RoleMapper RoleMapper;
	
	@Autowired
	public RoleService(RoleRepository questionTypeRepository, RoleMapper questionTypeMapper) {
		this.roleRepository = questionTypeRepository;
		this.RoleMapper = questionTypeMapper;
	}
	
	public List<RoleResponseDto> readAll() {
		return RoleMapper
					.mapToRoleResponseDtoList(roleRepository.findAll());
	}
	
	public RoleResponseDto readById(Long id) {
		return RoleMapper.map(roleRepository.findById(id).get());
	}
	
	public RoleResponseDto update(RoleRequestDto roleRequestDto) {
		
		Role role = RoleMapper.map(roleRequestDto);
		
		return RoleMapper.map(roleRepository.save(role));
	}
	
	public void delete(Long id) {
		
		if (roleRepository.existsById(id)) {
			roleRepository.deleteById(id);			
		} else {
			throw new RuntimeException("Role with " + id + " doesn't exist.");
		}
	}
	

}

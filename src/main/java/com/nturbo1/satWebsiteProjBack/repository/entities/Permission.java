package com.nturbo1.satWebsiteProjBack.repository.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "permissions")
@Getter
public class Permission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long permission_id;
	@Column
	private String permission_name;
	@Column
	private String description;

	@ManyToMany(mappedBy = "permissions")
	private List<Role> roles = new ArrayList<>();

	public Permission() {
	}

	public Long getPermission_id() {
		return permission_id;
	}

	public String getPermission_name() {
		return permission_name;
	}

	public String getDescription() {
		return description;
	}

	public List<Role> getRoles() {
		return roles;
	}
}

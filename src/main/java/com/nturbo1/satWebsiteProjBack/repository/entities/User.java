package com.nturbo1.satWebsiteProjBack.repository.entities;

import java.util.Collection;   
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "user_password")
	private String userPassword;
	@Column
	private String email;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
	    name = "user_roles",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roles;
	
	@OneToMany(mappedBy = "creator")
	private List<Test> createdTests;
	
	@OneToMany(mappedBy = "user")
	private List<UserTest> userTests;
	
	@OneToOne(mappedBy = "user")
	private Token token;
	
	private Set<Permission> getPermissions() {
		Set<Permission> permissions = new HashSet<>();
		
		for(Role role : roles) {
			for (Permission permission : role.getPermissions()) {
				permissions.add(permission);
			}
		}
		
		return permissions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
//	    var authorities = this.getPermissions() //TODO: Implement this.
//	            .stream()
//	            .map(permission -> new SimpleGrantedAuthority(permission.getPermission_name()))
//	            .collect(Collectors.toList());
	    
	    //authorities.add(new SimpleGrantedAuthority("ROLE_" /*+ this.name()*/));
		
		List<GrantedAuthority> authorities = this.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toList());
		
	    return authorities;
	}

	@Override
	public String getPassword() {
		return this.userPassword;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userPassword="
				+ userPassword + ", email=" + email + ", roles=" + roles + ", createdTests=" + createdTests
				+ ", userTests=" + userTests + "]";
	}
	
	
	
}

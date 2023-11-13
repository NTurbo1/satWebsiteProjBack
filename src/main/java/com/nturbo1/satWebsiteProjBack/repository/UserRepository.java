package com.nturbo1.satWebsiteProjBack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nturbo1.satWebsiteProjBack.repository.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	void deleteByEmail(String email);
	
	@Query("""
			select u from User u\s
			where exists \s
			(select r from u.roles r where r.roleName = 'STUDENT')\s
			""")
	List<User> findAllStudents();
	
	
	@Query("""
			select u from User u\s
			where u.userId = :userId and exists \s
			(select r from u.roles r where r.roleName = 'STUDENT')\s
			""") 
 	Optional<User> findStudentByUserId(@Param("userId") Long userId);
}

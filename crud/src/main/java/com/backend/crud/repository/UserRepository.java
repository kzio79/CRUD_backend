package com.backend.crud.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.crud.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	UserEntity findById(String id);
	UserEntity findByPw(String pw);
	UserEntity findByIdAndPw(String id, String pw);
	Boolean existsById(String id);

	@Transactional
	void deleteById(String id);
	
	@Modifying
	@Transactional
	@Query
	("UPDATE userlist u SET u.pw = :pw, u.name = :name, u.email = :email, u.phone = :phone, u.address = :address WHERE u.id = :id")
	void modifyProfile(@Param("id")String id, @Param("pw")String pw, @Param("name")String name, 
			@Param("email")String email, @Param("phone")String phone, @Param("address")String address);
}

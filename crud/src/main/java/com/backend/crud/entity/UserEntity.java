package com.backend.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "userlist")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usernum;
	
	@Column(name = "id", unique = true)
	private String id;
	
	@Column(name = "pw", nullable = false)
	private String pw;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "phone", unique = true)
	private String phone;
	
	@Column(name = "address")
	private String address;	
	
//	@OneToOne(mappedBy = "user")
//	private AccountEntity account;

}

package com.backend.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {

	private String id;
	private String pw;
	private String name;
	private String email;
	private String phone;
	private String address;
	
}

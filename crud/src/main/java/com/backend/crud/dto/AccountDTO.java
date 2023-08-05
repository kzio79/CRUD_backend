package com.backend.crud.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.backend.crud.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AccountDTO {
	
	private int accountnum;
	private int usernum;
	private int deposit;
	private String expense;
	private int expenseprice;
	private Timestamp accountdate;
}

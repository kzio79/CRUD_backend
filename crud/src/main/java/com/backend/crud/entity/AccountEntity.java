package com.backend.crud.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="accountlist")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountnum")
	private int accountnum;
	
	
	@Column(name = "deposit", nullable = false, columnDefinition = "int default 0")
	private int deposit;
	
	@Column(name = "expense")
	private String expense;
	
	@Column(name = "expenseprice", columnDefinition = "int default 0")
	private Integer expenseprice;
	
	@Column(name = "accountdate", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Timestamp accountdate;
	
	@ManyToOne
	@JoinColumn(name="usernum", referencedColumnName = "usernum", insertable = false, updatable = false)
	private UserEntity user;
}

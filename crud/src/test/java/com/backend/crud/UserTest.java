package com.backend.crud;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.crud.entity.AccountEntity;
import com.backend.crud.entity.UserEntity;
import com.backend.crud.repository.AccountRepository;
import com.backend.crud.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class UserTest {

		@Autowired
		UserRepository userRepository;
		
		@Autowired
		AccountRepository accountReposity;
		
		@Test
		public void testDependency() {
			System.out.println("주입 확인 : " + userRepository.getClass().getName());
		}

		@Test
		public void testInsert() {
			UserEntity user = UserEntity.builder()
					.id("testid1")
					.pw("testpw1")
					.name("테스트유저1")					
					.build();
			log.info(userRepository.save(user));
		}
		
		
		@Test
		public void testSelect() {
			UserEntity user = userRepository.findById("testid1");		
			log.info(user);
		}
		
		@Test
		public void testSelect2() {
			UserEntity user = userRepository.findByIdAndPw("testid1", "testpw1");
			System.out.println(user);
		}
		
		@Test
		public void checkId() {
			Boolean user = userRepository.existsById("testid1");
			
			if(user == true) {
				System.out.println("존재함");
			}else {
				System.out.println("없음");
			}
			
		}
		
		@Test
		public void testDelete() {
			userRepository.deleteById("testid1");
		}
		
		
		@Test
		public void accountInsert() {
			AccountEntity accountEntity = AccountEntity.builder()
					.accountnum(1)
					.deposit(100000)
					.build();
			accountReposity.save(accountEntity);
		}
		
		@Test
		public void accountTotalPrice() {

			int account = accountReposity.totalDeposit(8);
			System.out.println(account);		
		}
		
		@Test
		public void accountExpensePrice() {
			int account = accountReposity.totalExpensePrice(8);
			System.out.println(account);
		}
		
		@Test
		public void monthTotal() {
			int account = accountReposity.monthDeposit();
			System.out.println(account);
		}
		@Test
		public void monthTotalExpensePrice() {
			int account = accountReposity.monthExpensePrice();
			System.out.println(account);
		}
		
		@Test
		public void dailyDepoisit() {
			int account = accountReposity.dailyDeposit();
			System.out.println(account);
		}
		@Test
		public void printWeeklyExpensePrice() {
		    List<Object[]> weeklyExpensePrice = accountReposity.weeklyExpensePrice();
		    for (Object[] weekExpense : weeklyExpensePrice) {
		        System.out.println("Week " + weekExpense[0] + " Expense Price: " + weekExpense[1]);
		    }
		}
}

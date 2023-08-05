package com.backend.crud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.crud.dto.AccountDTO;
import com.backend.crud.entity.AccountEntity;
import com.backend.crud.entity.UserEntity;
import com.backend.crud.repository.AccountRepository;
import com.backend.crud.repository.UserRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	private String[] weekdayNames = {"월","화","수","목","금","토","일"};
	private String[] monthNames = {"1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"};
	
	//Save
	public AccountDTO addSave(AccountDTO accountDto) {

		try {
			UserEntity user = userService.findUser(accountDto.getUsernum());
			
			if(user != null) {
				AccountEntity account = AccountEntity.builder()
						.deposit(accountDto.getDeposit())
						.expense(accountDto.getExpense())
						.expenseprice(accountDto.getExpenseprice())
						.accountdate(accountDto.getAccountdate())
						.user(user)
						.build();
				
				accountRepository.save(account);
				System.out.println("account: "+account);				
			} else {
				System.out.println("회원가입을 해주세요");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return accountDto;				
	}
	
	//list불러오기
	public List<AccountDTO> totalList(){
		List<AccountEntity> accountlist = accountRepository.findAll();
		List<AccountDTO> account = accountlist.stream()
				.map(entity -> AccountDTO.builder()
						.deposit(entity.getDeposit())
						.expense(entity.getExpense())
						.expenseprice(entity.getExpenseprice())
						.build())
				.collect(Collectors.toList());
		
		System.out.println(accountlist);
		return account;
	}
	
	//현재 달 총 입금액
	public int monthDeposit() {
		int account = accountRepository.monthDeposit();
		System.out.println(account);
		return account;
	}
	
	//일별 입금액
	public int dailyDeposit(){
		int account = accountRepository.dailyDeposit();
		System.out.println(account);
		return account;
	}
	
	//일별 지출액
	public int dailyExpensePrice() {
		int account = accountRepository.dailyExpensePrice();
		System.out.println(account);
		return account;
	}
	
	//주간별 지출액
	public List<Map<String, Object>> weeklyExpensePrice() {
		List<Object[]> account = accountRepository.weeklyExpensePrice();
		List<Map<String, Object>> result = new ArrayList<>();
		
		for(Object[] weekExpense:account) {
			int weekIndex = ((Number)weekExpense[0]).intValue()%7;
			String weekday = weekdayNames[weekIndex];
			System.out.println(weekday+"요일 지출금액: "+weekExpense[1]);
			
			Map<String, Object> data = new HashMap<>();
			data.put("요일", weekday);
			data.put("지출금액", weekExpense[1]);
			result.add(data);
		}
		return result;
	}
	
	//월별 지출액
	public  List<Map<String, Object>> monthlyExpensePrice() {
		List<Object[]> account = accountRepository.monthlyExpensePrice();
		List<Map<String, Object>> result = new ArrayList<>();
		
		for(Object[] monthExpense:account) {
			int monthIndex = ((Number)monthExpense[0]).intValue()-1;
			String monthday = monthNames[monthIndex];
			System.out.println(monthday+"  지출금익: "+monthExpense[1]);
			
			Map<String, Object> data = new HashMap<>();
			data.put("월", monthday);
			data.put("지출금액", monthExpense[1]);
			result.add(data);
		}
		return result;
	}

}

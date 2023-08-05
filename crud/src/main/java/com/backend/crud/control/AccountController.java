package com.backend.crud.control;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.crud.dto.AccountDTO;
import com.backend.crud.entity.AccountEntity;
import com.backend.crud.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	//save
	@PostMapping("/save")
	public ResponseEntity<?> saveAll(@RequestBody AccountDTO accountDto){
		try {
			if(accountDto.getUsernum() != 0) {
				System.out.println("입력된 값");
				return ResponseEntity.ok().body(accountService.addSave(accountDto));
			}else {
				return ResponseEntity.badRequest().body("유저번호가 잘못됬음");
			}
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
		
	//현재 달 총 입금액
	@GetMapping("/monthdeposit")
	public ResponseEntity<?> monthDeposit(@RequestBody AccountEntity account){
		int monthDeposit = accountService.monthDeposit();
		return ResponseEntity.ok().body(monthDeposit);
	}
	
	//일별 입금액
	@GetMapping("/dailydeposit")
	public ResponseEntity<?> dailyDeposit(@RequestBody AccountEntity account){
		int dailyDeposit = accountService.dailyDeposit();
		return ResponseEntity.ok().body(dailyDeposit);
	}
	
	//일별 지출액
	@GetMapping("/dailyexpense")
	public ResponseEntity<?> dailyExpensePrice(@RequestBody AccountEntity account){
		int dailyExpensePrice = accountService.dailyExpensePrice();
		return ResponseEntity.ok().body(dailyExpensePrice);
	}
	
	//주간별 지출액
	@GetMapping("/weeklyexpense")
	public ResponseEntity<?> weeklyExpensePrice(@RequestBody AccountEntity account){
		List<Map<String, Object>> weeklyExpensePrice = accountService.weeklyExpensePrice();
		return ResponseEntity.ok().body(weeklyExpensePrice);
	}
	
	//월간별 지출액
	@GetMapping("/monthlyexpense")
	public ResponseEntity<?> monthlyExpensePrice(@RequestBody AccountEntity account){
		List<Map<String, Object>> monthlyExpensePrice = accountService.monthlyExpensePrice();
		return ResponseEntity.ok().body(monthlyExpensePrice);
	}
}

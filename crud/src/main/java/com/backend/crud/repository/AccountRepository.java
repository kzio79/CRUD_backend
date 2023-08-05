package com.backend.crud.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.crud.dto.AccountDTO;
import com.backend.crud.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
	
	List<AccountEntity> findAll();

	AccountEntity findByDeposit(int deposit);
	AccountEntity findByExpense(String expense);
	
	//현재 달 총 입금액
	@Query
	("SELECT SUM(a.deposit) FROM accountlist a WHERE YEAR(a.accountdate) = YEAR(CURRENT_DATE) AND MONTH(a.accountdate) = MONTH(CURRENT_DATE)")
	int monthDeposit();
	
	//현재 일별 입금액
	@Query
	("SELECT SUM(a.deposit) FROM accountlist a WHERE YEAR(a.accountdate) = YEAR(CURRENT_DATE) AND DAY(a.accountdate) = DAY(CURRENT_DATE)")
	int dailyDeposit();
	
		
	//현재달 총 지출액
	@Query
	("SELECT SUM(a.expenseprice) FROM accountlist a WHERE YEAR(a.accountdate) = YEAR(CURRENT_DATE) AND MONTH(a.accountdate) = MONTH(CURRENT_DATE)")
	int monthExpensePrice();
	
	//일별 지출액
	@Query
	("SELECT SUM(a.expenseprice) FROM accountlist a WHERE DATE(a.accountdate) = CURRENT_DATE")
	int dailyExpensePrice();
	
	//주별 지출액
	@Query
	("SELECT WEEK(a.accountdate) as week, SUM(a.expenseprice) FROM accountlist a WHERE YEAR(a.accountdate) = YEAR(CURRENT_DATE) GROUP BY week ORDER BY week")
	List<Object[]> weeklyExpensePrice();
	
	//월별 지출액
	@Query
	("SELECT MONTH(a.accountdate) as month, SUM(a.expenseprice) FROM accountlist a WHERE YEAR(a.accountdate) = YEAR(CURRENT_DATE) GROUP BY month ORDER BY month")
	List<Object[]> monthlyExpensePrice();
	
	//카테고리별 지출액
	@Query
	("SELECT COUNT(a.expense) FROM accountlist a WHERE YEAR(a.accountdate) = YEAR(CURRENT_DATE) AND MONTH(a.accountdate) = MONTH(CURRENT_DATE)")
	int categoryExpensePrice();	
		
}

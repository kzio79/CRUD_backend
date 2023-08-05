package com.backend.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.crud.dto.UserDTO;
import com.backend.crud.entity.UserEntity;
import com.backend.crud.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	//id 중복 체크
	public boolean checkedId(String id) {
		
		Boolean user = userRepository.existsById(id);		
		if(user == true) {
			System.out.println("id가 존재함");
			return true;
		}else {
			System.out.println("id가 없음");
		}
		return false;		
	}
	
	//id 체크
	public int login(String id, String pw) {
		
		UserEntity user = userRepository.findByIdAndPw(id, pw);
		
		if(user != null) {
			if(id.equals(user.getId()) && pw.equals(user.getPw())) {
				System.out.println("id와 pw가 같습니다");
			}else {
				System.out.println("id와 pwrk 다릅니다.");
			}
		}else {
			System.out.println("id를 입력하세요");
		}
		return 0;
	}

	//pw 체크
	public int loginPw(String pw) {
		
		UserEntity user = userRepository.findByPw(pw);
		
		if(user != null) {
			if(pw.equals(user.getPw())) {
				System.out.println("비밀번호가 같습니다");
			}else {
				System.out.println("비밀번호가 다릅니다.");
			}
		}else {
			System.out.println("비밀번호를 입력하세요");
		}
		return 0;
	}

	
	//회원가입
	public UserDTO insertSign(UserDTO userDto) {
		
		UserEntity user = UserEntity.builder()
				.id(userDto.getId())
				.pw(userDto.getPw())
				.name(userDto.getName())
				.email(userDto.getEmail())
				.phone(userDto.getPhone())
				.address(userDto.getAddress())
				.build();
		userRepository.save(user);
		return userDto;
	}
	
	//회원 profile보기
	public UserEntity listProfile(String id) {
		
		UserEntity user = userRepository.findById(id);
		return user;
	}
	
	//회원 수정
	public UserDTO modifyProfile(UserDTO userDto) {
				
		userRepository.modifyProfile(
				userDto.getId(),
				userDto.getPw(),
				userDto.getName(),
				userDto.getEmail(),
				userDto.getPhone(),
				userDto.getAddress());
		return userDto;
		
	}
	
	//로그인
	public UserEntity selectSign(String id, String pw) {
		
		UserEntity user = userRepository.findByIdAndPw(id, pw);
		return user;
	}
	
	//회원 삭제
	public void deleteUser(String id) {
		userRepository.deleteById(id); 
	}
	
	public UserEntity findUser(int id) {
		return userRepository.findById(id).orElse(null);
	}

}

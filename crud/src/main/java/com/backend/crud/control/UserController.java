package com.backend.crud.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.crud.dto.UserDTO;
import com.backend.crud.entity.UserEntity;
import com.backend.crud.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> checkedId(@RequestBody UserDTO user, UserEntity userEntity){
		if(userService.checkedId(user.getId()) == true) {
			System.out.println("id가 존재함(controller)");
			return ResponseEntity.badRequest().body(user.getId());
		}else {
			userEntity  = UserEntity.builder()
					.id(user.getId())
					.pw(user.getPw())
					.name(user.getName())
					.email(user.getEmail())
					.phone(user.getPhone())
					.address(user.getAddress())
					.build();
			System.out.println("입력된 값 : " + user);
			System.out.println("id가 없(controller)");
			return ResponseEntity.created(null).body(userService.insertSign(user));	
		}
	}
		
	@GetMapping("/login")
	public ResponseEntity<?> selectSing(@RequestBody UserDTO user){
		try {
			int result = userService.login(user.getId(), user.getPw());
			return ResponseEntity.ok().body(userService.selectSign(user.getId(), user.getPw()));
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/delete")
	public ResponseEntity<?> deleteUser(@RequestBody UserDTO user){
		userService.deleteUser(user.getId());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/profile")
	public ResponseEntity<?> listProfile(@RequestBody UserDTO userDto){
		
		userService.listProfile(userDto.getId());
		return ResponseEntity.ok().body(userService.listProfile(userDto.getId()));
	}
	
	@PutMapping("/modify")
	public ResponseEntity<?> modifyProfile(@RequestBody UserDTO userDto){
		try {
			System.out.println("수정성공");
			return ResponseEntity.ok().body(userService.modifyProfile(userDto));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정실패" + e.getMessage());
			return ResponseEntity.badRequest().build();
		}		
	}
}

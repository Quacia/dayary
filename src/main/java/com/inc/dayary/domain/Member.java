package com.inc.dayary.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Member {
	@Pattern(regexp="[a-z0-9]{4,10}", message="알파벳 또는 숫자 4~10글자")
	private String id;
	@Pattern(regexp="[A-Za-z0-9]{4,10}", message="알파벳 또는 숫자 4~10글자")
	private String password;
	private String passwordConfirm;
	@Pattern(regexp="[가-힣]{2,10}", message="한글 2~10글자")
	private String name;
	@NotEmpty(message="이메일을 입력해 주세요.")
	@Pattern(regexp="[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}" , message = "잘못된 이메일 형식 ex)apple@gmail.com")
	private String email;
	private String emailCode;
	@NotEmpty(message="성별을 선택해 주세요.")
	@Pattern(regexp="[fm]", message="잘못된 형식입니다.")
	private String gender;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isPasswordValid() {
		return this.password.equals(this.passwordConfirm);
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	
	
}

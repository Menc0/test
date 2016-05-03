package net.spring.model;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;


public class User2 {
	@NotEmpty(message="{user.name.error}")
	private String username;
	@Email(message="{user.email.geshi}")
	@NotEmpty(message="{user.email.error}")
	private String email;

	@Range(min = 1, max = 150 ,message="{user.age.error}")
	int age;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

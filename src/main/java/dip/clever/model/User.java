package dip.clever.model;

import java.util.Date;

import lombok.Data;

@Data
public class User{
	private String userId;
	private String userNickname;
	private String userPwd;
	private String userEmail;
	private Role userRole;
	private int userExp;
	private Date regTime;
}

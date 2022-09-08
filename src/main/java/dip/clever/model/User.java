package dip.clever.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import lombok.Data;

@Data
public class User{
	private String userId;
	private String userNickname;
	private String userPwd;
	private String userRole;
	private int userExp;
	private Date regTime;
}

package dip.clever.model;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
	private int replyId;
	private int replyNo;
	private String regUser;
	private String content;
	private Date regTime;
}

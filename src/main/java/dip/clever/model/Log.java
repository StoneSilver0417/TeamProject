package dip.clever.model;

import java.util.Date;

import lombok.Data;

@Data
public class Log {
	private String userId;
	private Action action;
	private Integer targetId;
	private Date regTime;
}

package dip.clever.model;

import java.util.Date;

import lombok.Data;

@Data
public class TestRound {
	private int roundNo;
	private int testNo;
	private String regUser;
	private String roundName;
	private Date testDate;
	private Date regTime;
}

package dip.clever.model;


import lombok.Data;

@Data
public class Quest {
	private int questNo;
	private int roundNo;
	private int questSeq;
	private String questContent;
	private int choiceNo;
	private String answer;
}

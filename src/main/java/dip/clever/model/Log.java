package dip.clever.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Log {
	private String userId;
	private Action action;
	private Integer targetId;
}

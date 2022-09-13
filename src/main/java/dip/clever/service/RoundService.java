package dip.clever.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dip.clever.model.Category;
import dip.clever.model.Round;
import dip.clever.model.SearchCondition;
import dip.clever.model.Test;

public interface RoundService {	
	public List<Round> selectRoundList(Test test);
	
	public Round selectRound(Round round);
}

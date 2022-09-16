package dip.clever.service;

import java.util.List;

import dip.clever.model.Log;
import dip.clever.model.Quest;

public interface LogService {
	public List<Quest> selectSolvedQuestList (Log log);
	
	public List<Quest> selectUploadQuestList(Log log);
}

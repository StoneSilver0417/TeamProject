package dip.clever.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;

import dip.clever.model.Log;
import dip.clever.model.Quest;
import dip.clever.service.LogService;

@Service
public class LogServiceImp implements LogService{

	@Override
	public List<Quest> selectSolvedQuestList(Log log) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quest> selectUploadQuestList(Log log) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.fifa.service;

import com.fifa.appcode.FifaTeam;
import com.fifa.official.RootObject;

public interface CommonService {

	FifaTeam getWinnerTeam(RootObject event);

}

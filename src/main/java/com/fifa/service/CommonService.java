package com.fifa.service;

import java.io.IOException;
import java.math.BigInteger;

import com.fifa.appcode.FifaTeam;
import com.fifa.model.User;
import com.fifa.official.RootObject;
import com.fifa.util.Output;

public interface CommonService {
	
	String getProfilePic(BigInteger userId);
	
	User getLoggerInUser();

	Output getUserBets();

	Output getLeaderboardUsingStoredProc() throws IOException;

	String getTrivia();

	String getDefaultImageForName(String name);

	String getTeamLogo(String teamCode) throws IOException;
	
	String getCompressedImageWithDefaultCompression(BigInteger userId) throws IOException;

	String getCompressedImage(BigInteger userId, int compressionFactor) throws IOException;
	
	String getImageFromBlobId(BigInteger blobId);

	FifaTeam getWinnerTeam(RootObject event);

}

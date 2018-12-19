package com.fifa.service.impl;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fifa.appcode.FifaTeam;
import com.fifa.appcode.ResponseCode;
import com.fifa.appcode.UserBetStatus;
import com.fifa.model.CustomBet;
import com.fifa.model.FifaBlob;
import com.fifa.model.FifaMatch;
import com.fifa.model.LeaderboardModel;
import com.fifa.model.TeamTrivia;
import com.fifa.model.User;
import com.fifa.model.UserBet;
import com.fifa.model.repository.CustomBetRepository;
import com.fifa.model.repository.FifaBlobRepository;
import com.fifa.model.repository.FifaMatchesRepository;
import com.fifa.model.repository.TeamTriviaRepository;
import com.fifa.model.repository.UserBetRepository;
import com.fifa.model.repository.UserRepository;
import com.fifa.official.AwayTeam;
import com.fifa.official.HomeTeam;
import com.fifa.official.RootObject;
import com.fifa.service.CommonService;
import com.fifa.util.Output;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CommonServiceImpl implements CommonService {
	private static final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	private static final String DEFAULT_FLAG_PIC = "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";
	private static final int COMPRESSION_FACTOR = 1;
	
	@Value("${fifa.send-base64-profile-pic:true}")
	private String base64;
	
	@Autowired
	private FifaBlobRepository blobRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomBetRepository customBetRepository;
	
	@Autowired
	private UserBetRepository userBetRepository;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private FifaMatchesRepository matchRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory; 
	
	@Autowired
	private TeamTriviaRepository triviaRepository;
	
	@Override
	public String getProfilePic(BigInteger userId) {
		if(base64.equalsIgnoreCase("FALSE"))
			return "<profile pic base64 string>";
		
		String profilePic = DEFAULT_FLAG_PIC;
		
		if(userId == null)
			return profilePic;
			
		Optional<User> ouser = userRepository.findById(userId);
		User user;
		BigInteger blobId;
		if(ouser.isPresent()) {
			user = ouser.get();
			blobId = user.getProPicId();
		}
		else return profilePic;
		
		if(blobId == null)
			return getDefaultImageForName(user.getName());
		
		Optional<FifaBlob> b = blobRepository.findById(blobId);
		if(b.isPresent()) {
			FifaBlob blob = b.get();
			profilePic = javax.xml.bind.DatatypeConverter.printBase64Binary(blob.getData());
		}
		return profilePic;
	}

	@Override
	public User getLoggerInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		User user = userRepository.findByMobileNo(username);
		Assert.notNull(user, "User not recognized");
		return user;
	}
	
	@Override
	public String getCompressedImageWithDefaultCompression(BigInteger userId) throws IOException {
		return getCompressedImage(userId, COMPRESSION_FACTOR);
	}
	
	@Override
	public String getCompressedImage(BigInteger userId, int compressionFactor) throws IOException {
		if(base64.equalsIgnoreCase("FALSE"))
			return "<profile pic base64 string>";
		
		String profilePic = DEFAULT_FLAG_PIC;
		
		if(userId == null)
			return profilePic;
			
		Optional<User> ouser = userRepository.findById(userId);
		User user;
		BigInteger blobId;
		if(ouser.isPresent()) {
			user = ouser.get();
			blobId = user.getProPicId();
		}
		else return profilePic;
		
		if(blobId == null)
			return getDefaultImageForName(user.getName());
		
		InputStream is;
		Optional<FifaBlob> b = blobRepository.findById(blobId);
		if(b.isPresent()) {
			FifaBlob blob = b.get();
			is = new ByteArrayInputStream(blob.getData());
		}
		else return profilePic;
		
		BufferedImage bi = ImageIO.read(is);
		
		int originalWidth = bi.getWidth();
        int originalHeight = bi.getHeight();
        int type = bi.getType() == 0? BufferedImage.TYPE_INT_ARGB : bi.getType();

        //rescale COMPRESSION_FACTOR times
        BufferedImage resizedImage = new BufferedImage(originalWidth/compressionFactor, originalHeight/compressionFactor, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(bi, 0, 0, originalWidth/compressionFactor, originalHeight/compressionFactor, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//        ImageIO.write(resizedImage, "jpg", new File("Lenna5050.jpg"));
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpeg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByte);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Output getUserBets(){
		Output out = new Output();
		
		User user = commonService.getLoggerInUser();
		BigInteger userId = user.getUserId();
		
//		check if the user has placed a bet
		Pageable oneRecord = new PageRequest(0, 1);
		List<UserBet> userExists = userBetRepository.findByUserId(user.getUserId(), oneRecord);
		if(userExists == null) {
			out.setResponseCode(ResponseCode.OK);
			out.setMessage("You have placed no bets so far, come on!");
			return out;
		}
		
//		find all concluded bets
		List<CustomBet> allConcludedBets = customBetRepository.findByBetResultCodeNotNull();
		List<MyPrediction> myPredictions = new ArrayList<>();	
		if(allConcludedBets == null) {
			out.setResponseCode(ResponseCode.OK);
			out.setMessage("We appreciate the enthusiasm but please be patient!");
			return out;
		}
		
		for(CustomBet b : allConcludedBets) {
			BigInteger betId = b.getBetId();
			UserBet bet = userBetRepository.findByUserIdAndBetId(userId, betId);
			if(bet == null) {
				bet = new UserBet();
				bet.setBetId(betId);
				bet.setMatchId(b.getMatchId());
				bet.setUserId(userId);
				bet.setUserBetStatus(UserBetStatus.NOBET);
				bet.setBetDesc(b.getBetDesc());
				bet.setBetCodeDesc("No Prediction by user");
			}

			Optional<FifaMatch> omatch = matchRepository.findById(b.getMatchId());
			FifaMatch match = new FifaMatch();
			if(omatch.isPresent())
				match = omatch.get();
			
			myPredictions.add(new MyPrediction(bet, match));
		}
		out.setData("myPredictions", myPredictions);
		out.setResponseCode(ResponseCode.OK);
		return out;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Output getLeaderboardUsingStoredProc() throws IOException{
		Output out = new Output();
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Session session = entityManager.unwrap(Session.class);
		
		Query<LeaderboardModel> query = session.getNamedQuery("leaderboardSummary");
		entityManager.joinTransaction();
		List<LeaderboardModel> model = query.list();
		
		session.close();
		
		List<ThisClass> rankList = new ArrayList<>();
		int rank = 0;
		for(LeaderboardModel m : model) {
			BigInteger userId = m.getUserId();
			Optional<User> ouser = userRepository.findById(userId);
			User user;
			if(ouser.isPresent())
				user = ouser.get();
			else {
				out.setMessage("Something went wrong");
				return out;
			}
			String profilePic = getCompressedImage(user.getUserId(), 8);
			user.setProfilePic(profilePic);
			
			ThisClass tc;
			if(m.getPoints() == 0)
				tc = new ThisClass(null, user, m);
			else tc = new ThisClass(++rank, user, m);
			
			rankList.add(tc);
		}
		
		out.setData("leaderboard", rankList);
		out.setResponseCode(ResponseCode.OK);
		return out;
	}
	
	@Override
	public String getTrivia(){
		String defaultTrivia = "David Villa has scored nine of the last 20 Spain World Cup goals and is one of the nine players to have scored in the last three competitions.";
		
		Random random = new Random();
		int bound = (int) triviaRepository.count();
		int id = random.nextInt(bound);
		
		if(id == 0)
			id += random.nextInt();
		
		Optional<TeamTrivia> trivia = triviaRepository.findById(new BigInteger(String.valueOf(id)));
		if(trivia.isPresent()) {
			TeamTrivia t = trivia.get();
			defaultTrivia = t.getTrivia();
		}
		
		return defaultTrivia;
	}
	
	@Override
	public String getDefaultImageForName(String name) {
		int imgWidth = 250;
		int imgHeight = 250;
		BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);

		Graphics2D g = img.createGraphics();
		
		int max = 5;
		int min = 1;
		Random random = new Random();
		int factorGreen = random.nextInt((max - min) + 1) + min;
		int factorBlue = random.nextInt((max - min) + 1) + min;
		
		Color backgroundColor = new Color(0, 50*factorGreen, 50*factorBlue);
		g.setPaint(backgroundColor);
		g.fillRect(0, 0, imgWidth, imgHeight);

		Font font = new Font("Arial", Font.PLAIN, 80);
		g.setFont(font);
		g.setPaint(Color.WHITE);

		String firstAndLastName[] = name.split(" ");
		String firstName = firstAndLastName[0].toUpperCase();
		String lastName = firstAndLastName[1].toUpperCase();
		
		String text = firstName.substring(0, 1) + lastName.substring(0, 1);

		TextLayout textLayout = new TextLayout(text, g.getFont(), g.getFontRenderContext());
		double textHeight = textLayout.getBounds().getHeight();
		double textWidth = textLayout.getBounds().getWidth();

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// Draw the text in the center of the image
		g.drawString(text, 	imgWidth /2 - (int)textWidth / 2,
							imgHeight / 2 + (int)textHeight / 2);

		String imgFormat = "png";
//		ImageIO.write(img, imgFormat, new File("Lenna5050.jpg"));
		
        try {
        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, imgFormat, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
	        baos.close();
	        return javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByte);
		} catch (IOException e) {
			log.error(e.getMessage());
			return DEFAULT_FLAG_PIC;
		}
	}
	
	@Override
	public String getTeamLogo(String teamCode) throws IOException{
		if(teamCode == null)
			return DEFAULT_FLAG_PIC;
		
		boolean validateTeamCode = false;
		for(FifaTeam t : FifaTeam.values())
			if(t.getCode().equals(teamCode))
				validateTeamCode = true;
		
		if(!validateTeamCode)
			return DEFAULT_FLAG_PIC;
		
		teamCode = teamCode.toLowerCase();
		
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url("https://api.fifa.com/api/v1/picture/flags-fwc2018-4/"+teamCode)
		  .get().build();
		
		Response response = client.newCall(request).execute();
		if(response == null || response.body() == null)
			return DEFAULT_FLAG_PIC;
		
		return javax.xml.bind.DatatypeConverter.printBase64Binary(response.body().bytes());
	}

	@Override
	public String getImageFromBlobId(BigInteger blobId) {
		if(base64.equalsIgnoreCase("FALSE"))
			return "<profile pic base64 string>";
		
		String profilePic = DEFAULT_FLAG_PIC;

		if(blobId == null)
			return profilePic;
		
		Optional<FifaBlob> b = blobRepository.findById(blobId);
		if(b.isPresent()) {
			FifaBlob blob = b.get();
			profilePic = javax.xml.bind.DatatypeConverter.printBase64Binary(blob.getData());
		}
		return profilePic;
	}
	
	@Override
	public FifaTeam getWinnerTeam(RootObject event) {
		String winner = event.getWinner();
		HomeTeam homeTeam = event.getHomeTeam();
		AwayTeam awayTeam = event.getAwayTeam();
		
		String idHomeTeam = homeTeam.getIdTeam();
		String idAwayTeam = awayTeam.getIdTeam();
		
		String winnerTeamCode;
		if(idHomeTeam.equals(winner))
			winnerTeamCode = homeTeam.getIdCountry();
		else if(idAwayTeam.equals(winner))
			winnerTeamCode = awayTeam.getIdCountry();
		else winnerTeamCode = "DRW";
		
		for(FifaTeam ft : FifaTeam.values())
			if(ft.getCode().equals(winnerTeamCode))
				return ft;
		
		return null;
	}
}

@SuppressWarnings("serial")
class ThisClass implements Serializable {
	Integer rank;
	User user;
	LeaderboardModel model;
	
	ThisClass(Integer rank, User user, LeaderboardModel leaderboardModel){
		this.rank = rank;
		this.user = user;
		this.model = leaderboardModel;
	}
	
	public String getName(){
		return this.user.getName();
	}
	
	public Long getPoints() {
		return this.model.getPoints();
	}
	
	public String getProfilePic() {
		return this.user.getProfilePic();
	}
	
	public Integer getRank() {
		return rank;
	}
	
	public BigInteger getUserId() {
		return this.user.getUserId();
	}
}

@SuppressWarnings("serial")
class MyPrediction implements Serializable {
	
	UserBet userBet;
	FifaMatch match;
	
	public MyPrediction(UserBet userBet, FifaMatch match) {
		this.userBet = userBet;
		this.match = match;
	}
	
	public BigInteger getMatchId(){
		return this.userBet.getMatchId();
	}
	
	public String getBetCode() {
		return this.userBet.getBetCode();
	}
	
	public UserBetStatus getUserBetStatus() {
		return this.userBet.getUserBetStatus();
	}
	
	public String getTeam1Name() {
		return this.match.getTeam1Name();
	}
	
	public String getTeam2Name() {
		return this.match.getTeam2Name();
	}
	
	public String getTeam1Code() {
		return this.match.getTeam1Code();
	}
	
	public String getTeam2Code() {
		return this.match.getTeam2Code();
	}
	
	public String getBetResult() {
		return this.match.getResult();
	}
	
	public String getBetTeamName(){
		String betCode = userBet.getBetCode();
		for(FifaTeam t : FifaTeam.values())
			if(t.getCode().equals(betCode))
				return String.valueOf(t);
		return betCode;
	}
	
	public Date getDate() {
		return this.match.getMatchDateTime();
	}
}
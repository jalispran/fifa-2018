//package com.fifa.config;
//
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//
//import org.hibernate.validator.constraints.NotBlank;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.stereotype.Component;
//
//@SuppressWarnings("deprecation")
//@Configuration
//@Component
//@ConfigurationProperties(prefix = "fifa")
//public class FifaEnvironmentConfig {
//	
//	private static final int DEFAULT_SERVER_PORT = 8080;
//	private static final String DEFAULT_PROFILE = "DEV";
//	
//	@Autowired
//	private ConfigurableEnvironment env;
//	
//	@Min(1025)
//	@Max(65536)
//	@Value("${server.port:8102}")
//	private int serverPort;
//
////	@Value("${fifa.production-url:http://192.168.1.40:8102/}")
//	private String productionUrl;
//
////	@Value("${fifa.update-url}")
//	private String updateUrl;
//
////	@Value("${fifa.api-version-no}")
//	private String apiVersionNo;
//
////	@Value("${fifa.tournament-winner:null}")
//	private String tournamentWinner;
//
////	@Value("${fifa.tournament-winner-pic:null}")
//	private String tournamentWinnerPic;
//
////	@Value("${fifa.tournament-winner-code:null}")
//	private String tournamentWinnerCode;
//
////	@Value("${fifa.neha-user-id:null}")
//	private String nehaUserId;
//
////	@Value("${fifa.pranjal-user-id:null}")
//	private String pranjalUserId;
//	
//	private boolean sendBase64ProfilePic;
//	
//	@NotBlank
//	@Value("${fifa.profile}")
//	private String profile;
//
//	@Bean
//	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> sessionManagerCustomizer() {
//		env.setActiveProfiles(profile);
//		
//		System.out.println("DEFAULT CONFIGURATIONS SET :");
//		System.out.println("\t SERVER PORT : " + getServerPort());
//		System.out.println("\t PRODUCTION URL : " + getProductionUrl());
//		System.out.println("\t UPDATE URL : " + getUpdateUrl());
//		System.out.println("\t API VERSION NUMBER : "+ getApiVersionNo());
//		System.out.println("\t TOURNAMENT WINNER : "+ getTournamentWinner());
//		System.out.println("\t TOURNAMENT WINNER PIC : " + getTournamentWinnerPic());
//		System.out.println("\t TOURNAMENT WINNER CODE : " + getTournamentWinnerCode());
//		System.out.println("\t NEHA USER ID : " + getNehaUserId());
//		System.out.println("\t PRANJAL USER ID : " + getPranjalUserId());
//		System.out.println("\t SEND BASE64 STRING FOR IMAGE : " + isSendBase64ProfilePic());
//		for(String p : env.getActiveProfiles())
//			System.out.println("\t ACTIVE PROFILES : " + p);
//		
//		return server -> server.setPort(getServerPort());
//	}
//
//	public int getServerPort() {
//		if(DEFAULT_PROFILE.equals(profile))
//			return serverPort;
//		return DEFAULT_SERVER_PORT;
//	}
//
//	public void setServerPort(int serverPort) {
//		this.serverPort = serverPort;
//	}
//
//	public String getProductionUrl() {
//		if(DEFAULT_PROFILE.equals(profile))
//			return "http://192.168.1.40:"+getServerPort()+"/";
//		else return productionUrl;
//	}
//
//	public void setProductionUrl(String productionUrl) {
//		this.productionUrl = productionUrl;
//	}
//
//	public String getUpdateUrl() {
//		return updateUrl;
//	}
//
//	public void setUpdateUrl(String updateUrl) {
//		this.updateUrl = updateUrl;
//	}
//
//	public String getApiVersionNo() {
//		return apiVersionNo;
//	}
//
//	public void setApiVersionNo(String apiVersionNo) {
//		this.apiVersionNo = apiVersionNo;
//	}
//
//	public String getTournamentWinner() {
//		return tournamentWinner;
//	}
//
//	public void setTournamentWinner(String tournamentWinner) {
//		this.tournamentWinner = tournamentWinner;
//	}
//
//	public String getTournamentWinnerPic() {
//		return tournamentWinnerPic;
//	}
//
//	public void setTournamentWinnerPic(String tournamentWinnerPic) {
//		this.tournamentWinnerPic = tournamentWinnerPic;
//	}
//
//	public String getTournamentWinnerCode() {
//		return tournamentWinnerCode;
//	}
//
//	public void setTournamentWinnerCode(String tournamentWinnerCode) {
//		this.tournamentWinnerCode = tournamentWinnerCode;
//	}
//
//	public String getNehaUserId() {
//		if(nehaUserId == null)
//			return "null";
//		return nehaUserId;
//	}
//
//	public void setNehaUserId(String nehaUserId) {
//		this.nehaUserId = nehaUserId;
//	}
//
//	public String getPranjalUserId() {
//		if(pranjalUserId == null)
//			return "null";
//		return pranjalUserId;
//	}
//	
//	public void setPranjalUserId(String pranjalUserId) {
//		this.pranjalUserId = pranjalUserId;
//	}
//	
//	public boolean isSendBase64ProfilePic() {
//		if(DEFAULT_PROFILE.equals(profile) && sendBase64ProfilePic)
//			return false;
//		return sendBase64ProfilePic;
//	}
//	
//	public void setSendBase64ProfilePic(boolean sendBase64ProfilePic) {
//		this.sendBase64ProfilePic = sendBase64ProfilePic;
//	}
//	
//}

package com.fifa.appcode;

public enum FifaTeam {

	ARGENTINA("ARG"),
	
	AUSTRALIA("AUS"),
	
	BELGIUM("BEL"),
	
	BRAZIL("BRA"),
	
	COLOMBIA("COL"),
	
	COSTA_RICA("CRC"),
	
	CROATIA("CRO"),
	
	DENMARK("DEN"),
	
	EGYPT("EGY"),
	
	ENGLAND("ENG"),
	
	FRANCE("FRA"),
	
	GERMANY("GER"),
	
	ICELAND("ISL"),
	
	IRAN("IRN"),
	
	JAPAN("JPN"),
	
	KOREA_REPUBLIC("KOR"),
	
	MEXICO("MEX"),
	
	MOROCCO("MAR"),
	
	NIGERIA("NGA"),
	
	PANAMA("PAN"),
	
	PERU("PER"),
	
	POLAND("POL"),
	
	PORTUGAL("POR"),
	
	RUSSIA("RUS"),
	
	SAUDI_ARABIA("KSA"),
	
	SENEGAL("SEN"),
	
	SERBIA("SRB"),
	
	SPAIN("ESP"),
	
	SWEDEN("SWE"),
	
	SWITZERLAND("SUI"),
	
	TUNISIA("TUN"),
	
	URUGUAY("URU"),
	
	DRAW("DRW");
	
	
	private String code;
	
	private FifaTeam(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}

package com.fifa.appcode;

public enum ResponseCode{

	OK("0000", "OK"),
	ERR_DUPREC("0001", "Duplicate Record | Already registered"),
	ERR_RECNF("0002", "Record Not Found | Record not found"),
	ERR_CONC("0003", "Concurreny Error in Update | Update failed"),
	STRT_OF_SELCTN("0004", "At Start of Selection | Something went wrong"),
	END_OF_SELCTN("0005", "At End of Selection | Something went wrong"),
	ERR_APLCAN("0101", "Application Error | Something went wrong"),
	UNAUTHORIZED("0006", "Unauthorized Error | Full authentication is required to access this resource"),
	
	ERR_INVALID_BET("0102", "Invalid bet"),
	
	INVALD_MSG("0101" ,"Invalid Message Date/Time"),
	INVALD_MAPARTY("0050", "Invalid MAPartyId"),
	INVALID_MAMERID("0101", "Invalid or Missing MAMerchantId"),
	INVALID_MOBNO("0102", "Invalid MobileNo"),
	INVALID_PTY_TYPE("0103", "Invalid PartyType"),
	INVALID_COUNTRY_CODE("0104", "Invalid CountryCode"),
	INVALID_LANG_CODE("0105", "Invalid LanguageCode"),
	INVALID_LOC_TYPE("0106", "Invalid LocationType"),
	INVALID_CORP_TYPE("0107", "Invalid CorpType"),
	INVALID_INCORP_DT("0108", "Invalid IncorporationDate"),
	INVALID_MCC_CODE("0109", "Invalid MCCCode"),
	INVALID_SETT_CODE("0110", "Invalid SettlementCcy"),
	INVALID_MULTI_CCY_YN("0111", "Invalid MultiCcyYn"),
	INVALID_ADDR("0112", "Missing AddressLine1"),
	INVALID_CITY("0113", "Missing City"),
	INVALID_PINZIP("0114", "Missing PinZip"),
	INVALID_EMAIL("0115", "Invalid EmailAddress"),
	INVALID_LATLONG("0116", "Invalid LatLong"),
	INVALID_TIME_ZONE("0117", "Invalid or Missing TimeZoneCode"),
	INVALID_ADDR_SINCE("0118", "Invalid AddressSince"),
	INVALID_ADDR_OWN_RNT("0119", "Invalid AddressOwnRent"),
	INVALID_HRS_WK_DY("0120", "Invalid HoursWeekDay"),
	INVALID_HRS_WK_END("0121", "Invalid HoursWeekEnd"),
	INVALID_MA_LOC_CODE("0122", "Invalid or Missing MALocationCode"),
	INVALID_MER_ID("0201", "Merchant Id Not Found"),
	ERR_LOC("0202", "Location Code Not Found"),
	INVALID_TID("0211", "Invalid TID"),
	INVALID_TXN_AMT("0212", "Invalid TxnAmount"),
	INVALID_TNX_CCY("0213", "Invalid TxnCcy"),
	INVALID_INSTR_CODE("0214", "Invalid InstrumentCode"),
	INVALID_IP("0215", "Invalid SourceIPAddress"),
	INVALID_URL("0216", "Invalid WebUrl"),
	INVALID_RESP_CODE("0251", "Invalid ResponseCode"),
	INVALID_TXN_REFNO("0252", "Invalid TxnAuthRefNo"),
	INVALID_SET_AMT("0353", "Invalid SettlementAmount"),
	INVALID_SET_CCY("0354", "Invalid SettlementCcy"),
	INVALID_EXCH_RATE("0355", "Invalid ExchangeRate"),
	ERR_EXCH_RATE("0356", "Incorrect ExchangeRate"),
	INVALID_REC_TYPE("1001", "Invalid BodyRecordType"),
	ERR_REC("1002", "Record Not Missing"),
	ERR_REC_NT_EXTR("1003", "Record Not Extra"),
	ERR_NO_REC("1004", "Record No Mismatch"),
	ERR_MER_SUSP("1151", "Merchant Already Suspended"),
	ERR_MER_NT_SUSP("1152", "Merchant Not Suspended"),
	ERR_MA_SER("2001", "Merchant Aggregator Server Error"),
	;
	
	private String code;
	private String description;
	
	ResponseCode(String code, String description){
		this.code = code;
		this. description = description;
	}
	
	public String getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}

}


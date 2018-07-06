package com.fifa.appcode.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fifa.appcode.UserBetStatus;

@Converter(autoApply = true)
public class BetStatusConverter implements AttributeConverter<UserBetStatus, String>{
	@Override
	public String convertToDatabaseColumn(UserBetStatus attribute) {
		return attribute.getCode();
	}


	@Override
	public UserBetStatus convertToEntityAttribute(String dbData) {
		for(UserBetStatus cd : UserBetStatus.values())
			if(cd.getCode().equals(dbData))
				return cd;
		return null;
	}
}
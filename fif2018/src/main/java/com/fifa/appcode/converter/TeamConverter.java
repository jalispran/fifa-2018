package com.fifa.appcode.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fifa.appcode.FifaTeam;

@Converter(autoApply = true)
public class TeamConverter implements AttributeConverter<FifaTeam, String>{
	@Override
	public String convertToDatabaseColumn(FifaTeam attribute) {
		return attribute.getCode();
}


	@Override
	public FifaTeam convertToEntityAttribute(String dbData) {
		for(FifaTeam cd : FifaTeam.values())
			if(cd.getCode().equals(dbData))
				return cd;
		return null;
}
}

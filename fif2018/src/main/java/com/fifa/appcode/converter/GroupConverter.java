package com.fifa.appcode.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fifa.appcode.FifaGroup;

@Converter(autoApply = true)
public class GroupConverter implements AttributeConverter<FifaGroup, String>{

	@Override
	public String convertToDatabaseColumn(FifaGroup attribute) {
		return String.valueOf(attribute);
	}

	@Override
	public FifaGroup convertToEntityAttribute(String dbData) {
		for(FifaGroup f : FifaGroup.values())
			if(String.valueOf(f).equals(dbData))
				return f;
		return null;
	}

}

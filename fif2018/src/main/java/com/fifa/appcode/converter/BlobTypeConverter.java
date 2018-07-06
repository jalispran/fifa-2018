package com.fifa.appcode.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fifa.appcode.FifaBlobType;

@Converter(autoApply = true)
public class BlobTypeConverter implements AttributeConverter<FifaBlobType, String>{
	@Override
	public String convertToDatabaseColumn(FifaBlobType attribute) {
		return attribute.getCode();
	}


	@Override
	public FifaBlobType convertToEntityAttribute(String dbData) {
		for(FifaBlobType cd : FifaBlobType.values())
			if(cd.getCode().equals(dbData))
				return cd;
		return null;
	}
}
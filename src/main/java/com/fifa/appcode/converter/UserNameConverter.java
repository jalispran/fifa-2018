package com.fifa.appcode.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fifa.model.User;

@Converter(autoApply = true)
public class UserNameConverter implements AttributeConverter<User, User>{

	@Override
	public User convertToDatabaseColumn(User attribute) {
		String name = attribute.getName();
		attribute.setName(name.toUpperCase());
		return attribute;
	}

	@Override
	public User convertToEntityAttribute(User dbData) {
		return dbData;
	}

}

package com.fifa.official;

import java.util.ArrayList;

public class Official
{
	  private String IdCountry;

	  public String getIdCountry() { return this.IdCountry; }

	  public void setIdCountry(String IdCountry) { this.IdCountry = IdCountry; }

	  private String OfficialId;

	  public String getOfficialId() { return this.OfficialId; }

	  public void setOfficialId(String OfficialId) { this.OfficialId = OfficialId; }

	  private ArrayList<NameShort> NameShort;

	  public ArrayList<NameShort> getNameShort() { return this.NameShort; }

	  public void setNameShort(ArrayList<NameShort> NameShort) { this.NameShort = NameShort; }

	  private ArrayList<Name> Name;

	  public ArrayList<Name> getName() { return this.Name; }

	  public void setName(ArrayList<Name> Name) { this.Name = Name; }

	  private int OfficialType;

	  public int getOfficialType() { return this.OfficialType; }

	  public void setOfficialType(int OfficialType) { this.OfficialType = OfficialType; }

	  private ArrayList<TypeLocalized> TypeLocalized;

	  public ArrayList<TypeLocalized> getTypeLocalized() { return this.TypeLocalized; }

	  public void setTypeLocalized(ArrayList<TypeLocalized> TypeLocalized) { this.TypeLocalized = TypeLocalized; }
	}
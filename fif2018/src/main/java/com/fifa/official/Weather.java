package com.fifa.official;

import java.util.ArrayList;

public class Weather
{
	private String Humidity;

	public String getHumidity() { return this.Humidity; }

	public void setHumidity(String Humidity) { this.Humidity = Humidity; }

	private String Temperature;

	public String getTemperature() { return this.Temperature; }

	public void setTemperature(String Temperature) { this.Temperature = Temperature; }

	private String WindSpeed;

	public String getWindSpeed() { return this.WindSpeed; }

	public void setWindSpeed(String WindSpeed) { this.WindSpeed = WindSpeed; }

	private int Type;

	public int getType() { return this.Type; }

	public void setType(int Type) { this.Type = Type; }

	private ArrayList<TypeLocalized> TypeLocalized;

	public ArrayList<TypeLocalized> getTypeLocalized() { return this.TypeLocalized; }

	public void setTypeLocalized(ArrayList<TypeLocalized> TypeLocalized) { this.TypeLocalized = TypeLocalized; }
}

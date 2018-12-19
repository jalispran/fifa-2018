package com.fifa.official;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

class Alias
{
	private String Locale;

	public String getLocale() { return this.Locale; }

	public void setLocale(String Locale) { this.Locale = Locale; }

	private String Description;

	public String getDescription() { return this.Description; }

	public void setDescription(String Description) { this.Description = Description; }
}

class Coach
{
	private String IdCoach;

	public String getIdCoach() { return this.IdCoach; }

	public void setIdCoach(String IdCoach) { this.IdCoach = IdCoach; }

	private String IdCountry;

	public String getIdCountry() { return this.IdCountry; }

	public void setIdCountry(String IdCountry) { this.IdCountry = IdCountry; }

	private ArrayList<Name> Name;

	public ArrayList<Name> getName() { return this.Name; }

	public void setName(ArrayList<Name> Name) { this.Name = Name; }

	private ArrayList<Alias> Alias;

	public ArrayList<Alias> getAlias() { return this.Alias; }

	public void setAlias(ArrayList<Alias> Alias) { this.Alias = Alias; }

	private int Role;

	public int getRole() { return this.Role; }

	public void setRole(int Role) { this.Role = Role; }
}

class ShortName
{
	private String Locale;

	public String getLocale() { return this.Locale; }

	public void setLocale(String Locale) { this.Locale = Locale; }

	private String Description;

	public String getDescription() { return this.Description; }

	public void setDescription(String Description) { this.Description = Description; }
}

class PlayerOffName
{
	private String Locale;

	public String getLocale() { return this.Locale; }

	public void setLocale(String Locale) { this.Locale = Locale; }

	private String Description;

	public String getDescription() { return this.Description; }

	public void setDescription(String Description) { this.Description = Description; }
}

class PlayerOnName
{
	private String Locale;

	public String getLocale() { return this.Locale; }

	public void setLocale(String Locale) { this.Locale = Locale; }

	private String Description;

	public String getDescription() { return this.Description; }

	public void setDescription(String Description) { this.Description = Description; }
}

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootObject
{
	@JsonProperty("Stadium")
	private Stadium Stadium;

	public Stadium getStadium() { return this.Stadium; }

	public void setStadium(Stadium Stadium) { this.Stadium = Stadium; }

	@JsonProperty("ResultType")
	private int ResultType;

	public int getResultType() { return this.ResultType; }

	public void setResultType(int ResultType) { this.ResultType = ResultType; }

	@JsonProperty("MatchDay")
	private String MatchDay;

	public String getMatchDay() { return this.MatchDay; }

	public void setMatchDay(String MatchDay) { this.MatchDay = MatchDay; }

	@JsonProperty("IdMatch")
	private String IdMatch;

	public String getIdMatch() { return this.IdMatch; }

	public void setIdMatch(String IdMatch) { this.IdMatch = IdMatch; }

	@JsonProperty("IdStage")
	private String IdStage;

	public String getIdStage() { return this.IdStage; }

	public void setIdStage(String IdStage) { this.IdStage = IdStage; }

	@JsonProperty("IdGroup")
	private String IdGroup;

	public String getIdGroup() { return this.IdGroup; }

	public void setIdGroup(String IdGroup) { this.IdGroup = IdGroup; }

	@JsonProperty("IdSeason")
	private String IdSeason;

	public String getIdSeason() { return this.IdSeason; }

	public void setIdSeason(String IdSeason) { this.IdSeason = IdSeason; }

	@JsonProperty("IdCompetition")
	private String IdCompetition;

	public String getIdCompetition() { return this.IdCompetition; }

	public void setIdCompetition(String IdCompetition) { this.IdCompetition = IdCompetition; }

	@JsonProperty("HomeTeamPenaltyScore")
	private int HomeTeamPenaltyScore;

	public int getHomeTeamPenaltyScore() { return this.HomeTeamPenaltyScore; }

	public void setHomeTeamPenaltyScore(int HomeTeamPenaltyScore) { this.HomeTeamPenaltyScore = HomeTeamPenaltyScore; }

	@JsonProperty("AwayTeamPenaltyScore")
	private int AwayTeamPenaltyScore;

	public int getAwayTeamPenaltyScore() { return this.AwayTeamPenaltyScore; }

	public void setAwayTeamPenaltyScore(int AwayTeamPenaltyScore) { this.AwayTeamPenaltyScore = AwayTeamPenaltyScore; }

	@JsonProperty("AggregateHomeTeamScore")
	private int AggregateHomeTeamScore;

	public int getAggregateHomeTeamScore() { return this.AggregateHomeTeamScore; }

	public void setAggregateHomeTeamScore(int AggregateHomeTeamScore) { this.AggregateHomeTeamScore = AggregateHomeTeamScore; }

	@JsonProperty("AggregateAwayTeamScore")
	private int AggregateAwayTeamScore;

	public int getAggregateAwayTeamScore() { return this.AggregateAwayTeamScore; }

	public void setAggregateAwayTeamScore(int AggregateAwayTeamScore) { this.AggregateAwayTeamScore = AggregateAwayTeamScore; }

	@JsonProperty("Weather")
	private Weather Weather;

	public Weather getWeather() { return this.Weather; }

	public void setWeather(Weather Weather) { this.Weather = Weather; }

	@JsonProperty("Attendance")
	private String Attendance;

	public String getAttendance() { return this.Attendance; }

	public void setAttendance(String Attendance) { this.Attendance = Attendance; }

	@JsonProperty("Date")
	private Date Date;

	public Date getDate() { return this.Date; }

	public void setDate(Date Date) { this.Date = Date; }

	@JsonProperty("LocalDate")
	private Date LocalDate;

	public Date getLocalDate() { return this.LocalDate; }

	public void setLocalDate(Date LocalDate) { this.LocalDate = LocalDate; }

	@JsonProperty("MatchTime")
	private String MatchTime;

	public String getMatchTime() { return this.MatchTime; }

	public void setMatchTime(String MatchTime) { this.MatchTime = MatchTime; }

	@JsonProperty("SecondHalfTime")
	private Object SecondHalfTime;

	public Object getSecondHalfTime() { return this.SecondHalfTime; }

	public void setSecondHalfTime(Object SecondHalfTime) { this.SecondHalfTime = SecondHalfTime; }

	@JsonProperty("FirstHalfTime")
	private Object FirstHalfTime;

	public Object getFirstHalfTime() { return this.FirstHalfTime; }

	public void setFirstHalfTime(Object FirstHalfTime) { this.FirstHalfTime = FirstHalfTime; }

	@JsonProperty("FirstHalfExtraTime")
	private Object FirstHalfExtraTime;

	public Object getFirstHalfExtraTime() { return this.FirstHalfExtraTime; }

	public void setFirstHalfExtraTime(Object FirstHalfExtraTime) { this.FirstHalfExtraTime = FirstHalfExtraTime; }

	@JsonProperty("SecondHalfExtraTime")
	private Object SecondHalfExtraTime;

	public Object getSecondHalfExtraTime() { return this.SecondHalfExtraTime; }

	public void setSecondHalfExtraTime(Object SecondHalfExtraTime) { this.SecondHalfExtraTime = SecondHalfExtraTime; }

	@JsonProperty("Winner")
	private String Winner;

	public String getWinner() { return this.Winner; }

	public void setWinner(String Winner) { this.Winner = Winner; }

	@JsonProperty("Period")
	private int Period;

	public int getPeriod() { return this.Period; }

	public void setPeriod(int Period) { this.Period = Period; }

	@JsonProperty("HomeTeam")
	private HomeTeam HomeTeam;

	public HomeTeam getHomeTeam() { return this.HomeTeam; }

	public void setHomeTeam(HomeTeam HomeTeam) { this.HomeTeam = HomeTeam; }

	@JsonProperty("AwayTeam")
	private AwayTeam AwayTeam;

	public AwayTeam getAwayTeam() { return this.AwayTeam; }

	public void setAwayTeam(AwayTeam AwayTeam) { this.AwayTeam = AwayTeam; }

	@JsonProperty("BallPossession")
	private BallPossession BallPossession;

	public BallPossession getBallPossession() { return this.BallPossession; }

	public void setBallPossession(BallPossession BallPossession) { this.BallPossession = BallPossession; }

	@JsonProperty("TerritorialPossesion")
	private Object TerritorialPossesion;

	public Object getTerritorialPossesion() { return this.TerritorialPossesion; }

	public void setTerritorialPossesion(Object TerritorialPossesion) { this.TerritorialPossesion = TerritorialPossesion; }
	
	@JsonProperty("TerritorialThirdPossesion")
	private Object TerritorialThirdPossesion;

	public Object getTerritorialThirdPossesion() { return this.TerritorialThirdPossesion; }

	public void setTerritorialThirdPossesion(Object TerritorialThirdPossesion) { this.TerritorialThirdPossesion = TerritorialThirdPossesion; }

	@JsonProperty("Officials")
	private ArrayList<Official> Officials;

	public ArrayList<Official> getOfficials() { return this.Officials; }

	public void setOfficials(ArrayList<Official> Officials) { this.Officials = Officials; }

	@JsonProperty("MatchStatus")
	private int MatchStatus;

	public int getMatchStatus() { return this.MatchStatus; }

	public void setMatchStatus(int MatchStatus) { this.MatchStatus = MatchStatus; }

	@JsonProperty("GroupName")
	private ArrayList<GroupName> GroupName;

	public ArrayList<GroupName> getGroupName() { return this.GroupName; }

	public void setGroupName(ArrayList<GroupName> GroupName) { this.GroupName = GroupName; }

	@JsonProperty("StageName")
	private ArrayList<StageName> StageName;

	public ArrayList<StageName> getStageName() { return this.StageName; }

	public void setStageName(ArrayList<StageName> StageName) { this.StageName = StageName; }

	@JsonProperty("OfficialityStatus")
	private int OfficialityStatus;

	public int getOfficialityStatus() { return this.OfficialityStatus; }

	public void setOfficialityStatus(int OfficialityStatus) { this.OfficialityStatus = OfficialityStatus; }

	@JsonProperty("TimeDefined")
	private boolean TimeDefined;

	public boolean getTimeDefined() { return this.TimeDefined; }

	public void setTimeDefined(boolean TimeDefined) { this.TimeDefined = TimeDefined; }

	@JsonProperty("Properties")
	private Properties Properties;

	public Properties getProperties() { return this.Properties; }

	public void setProperties(Properties Properties) { this.Properties = Properties; }

	@JsonProperty("IsUpdateable")
	private Object IsUpdateable;

	public Object getIsUpdateable() { return this.IsUpdateable; }

	public void setIsUpdateable(Object IsUpdateable) { this.IsUpdateable = IsUpdateable; }

	@Override
	public String toString() {
		return "RootObject [Stadium=" + Stadium + ", ResultType=" + ResultType + ", MatchDay=" + MatchDay + ", IdMatch="
				+ IdMatch + ", IdStage=" + IdStage + ", IdGroup=" + IdGroup + ", IdSeason=" + IdSeason + ", IdCompetition="
				+ IdCompetition + ", HomeTeamPenaltyScore=" + HomeTeamPenaltyScore + ", AwayTeamPenaltyScore="
				+ AwayTeamPenaltyScore + ", AggregateHomeTeamScore=" + AggregateHomeTeamScore + ", AggregateAwayTeamScore="
				+ AggregateAwayTeamScore + ", Weather=" + Weather + ", Attendance=" + Attendance + ", Date=" + Date
				+ ", LocalDate=" + LocalDate + ", MatchTime=" + MatchTime + ", SecondHalfTime=" + SecondHalfTime
				+ ", FirstHalfTime=" + FirstHalfTime + ", FirstHalfExtraTime=" + FirstHalfExtraTime
				+ ", SecondHalfExtraTime=" + SecondHalfExtraTime + ", Winner=" + Winner + ", Period=" + Period
				+ ", HomeTeam=" + HomeTeam + ", AwayTeam=" + AwayTeam + ", BallPossession=" + BallPossession
				+ ", TerritorialPossesion=" + TerritorialPossesion + ", TerritorialThirdPossesion="
				+ TerritorialThirdPossesion + ", Officials=" + Officials + ", MatchStatus=" + MatchStatus + ", GroupName="
				+ GroupName + ", StageName=" + StageName + ", OfficialityStatus=" + OfficialityStatus + ", TimeDefined="
				+ TimeDefined + ", Properties=" + Properties + ", IsUpdateable=" + IsUpdateable + "]";
	}

}

package characters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gladiator implements Comparable<Gladiator>{

	private String name;
	private float handStrikeDamage;
	private float footStrikeDamage;
	private float handStrikeResistance;
	private float footStrikeResistance;
	private float arrowResistance;
	private float totalResistance;
	
	private String kind;
	private String gender;
	
	private float stamina;
	private float maxSpeed;
	
	private float time;
	private float score;
	
	private float sexualAttraction;
	private float beauty;
	private float intelligence;

	public Gladiator(@JsonProperty("name") String name, @JsonProperty("kind")String kind, @JsonProperty("gender")String gender,
					 @JsonProperty("handStrikeDamage") float handStrikeDamage, @JsonProperty("footStrikeDamage") float footStrikeDamage,
					 @JsonProperty("handStrikeResistance") float handStrikeResistance, @JsonProperty("footStrikeResistance") float footStrikeResistance,
					 @JsonProperty("arrowResistance") float arrowResistance, @JsonProperty("totalResistance") float totalResistance,
					 @JsonProperty("stamina") float stamina, @JsonProperty("maxSpeed") float maxSpeed, @JsonProperty("time") float time,
					 @JsonProperty("score") float score, @JsonProperty("sexualAttraction") float sexualAttraction, @JsonProperty("beauty") float beauty,
					 @JsonProperty("intelligence") float intelligence){
		this.name = name;
		this.gender = gender;
		this.kind = kind;
		this.handStrikeDamage = handStrikeDamage;
		this.footStrikeDamage = footStrikeDamage;
		this.handStrikeResistance = handStrikeResistance;
		this.footStrikeResistance = footStrikeResistance;
		this.arrowResistance = arrowResistance;
		this.totalResistance = totalResistance;
		this.stamina = stamina;
		this.maxSpeed = maxSpeed;
		this.time = time;
		this.score = score;
		this.sexualAttraction = sexualAttraction;
		this.beauty = beauty;
		this.intelligence = intelligence;
	}

	@Override
	public int compareTo(Gladiator otherGladiator) {
		if(this.score > otherGladiator.score){
			return 1;
		}
		else if (this.score < otherGladiator.score){
			return -1;
		}
		else{
			return 0;
		}

	}
	public String toString(){
		return String.format("Name: %s\n"
				+ "Gender: %s\n"
				+ "Hand Strike Damage: %f\n"
				+ "Foot Strike Damage: %f\n"
				+ "Hand StrikeResistance: %f\n"
				+ "Foot Strike Resistance: %f\n"
				+ "Arrow Resistance: %f\n"
				+ "TotalResistance: %f\n"
				+ "Kind: %s\n"
				+ "Stamina: %f\n"
				+ "Maximun Speed: %f\n"
				+ "Score: %f\n"
				+ "Sexual Attraction: %f\n"
				+ "Beauty: %f\n"
				+ "Intelligence: %f\n", 
				name, gender, handStrikeDamage, footStrikeDamage,
				handStrikeResistance, footStrikeResistance, arrowResistance, 
				this.totalResistance, kind, stamina, maxSpeed, 
				score, sexualAttraction, beauty, intelligence);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public float getHandStrikeDamage() {
		return handStrikeDamage;
	}
	public void setHandStrikeDamage(float handStrikeDamage) {
		this.handStrikeDamage = handStrikeDamage;
	}
	
	public float getFootStrikeDamage() {
		return footStrikeDamage;
	}
	public void setFootStrikeDamage(float footStrikeDamage) {
		this.footStrikeDamage = footStrikeDamage;
	}
	
	public float getHandStrikeResistance() {
		return handStrikeResistance;
	}
	public void setHandStrikeResistance(float handStrikeResistance) {
		this.handStrikeResistance = handStrikeResistance;
	}
	
	public float getFootStrikeResistance() {
		return footStrikeResistance;
	}
	public void setFootStrikeResistance(float footStrikeResistance) {
		this.footStrikeResistance = footStrikeResistance;
	}
	
	public float getArrowResistance() {
		return arrowResistance;
	}
	public void setArrowResistance(float arrowResistance) {
		this.arrowResistance = arrowResistance;
	}
	
	public float getTotalResistance() {
		return totalResistance;
	}
	public void setTotalResistance(float totalResistance) {
		this.totalResistance = totalResistance;
	}
	
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public float getStamina() {
		return stamina;
	}
	public void setStamina(float stamina) {
		this.stamina = stamina;
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	public float getSexualAttraction() {
		return sexualAttraction;
	}
	public void setSexualAttraction(float sexualAttraction) {
		this.sexualAttraction = sexualAttraction;
	}
	
	public float getBeauty() {
		return beauty;
	}
	public void setBeauty(float beauty) {
		this.beauty = beauty;
	}
	
	public float getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(float intelligence) {
		this.intelligence = intelligence;
	}
	
}

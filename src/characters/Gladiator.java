package characters;

import algorithms.Randomizer;
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

	public Gladiator(String name,String kind,String gender) {
		this.name = name;		
		this.gender = gender;
		this.kind = kind;
		this.setDamageResistance();
		this.setDerivatedAttributes();
		this.setMiscelaneousAttributes();
	}

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
	
	public Gladiator(String gender, String name, Gladiator[] parents){
		
		this.name = name;
		this.handStrikeDamage = parents[Randomizer.getPosInt(parents.length)].getHandStrikeDamage();
		this.footStrikeDamage = parents[Randomizer.getPosInt(parents.length)].getFootStrikeDamage();
		this.handStrikeResistance = parents[Randomizer.getPosInt(parents.length)].getHandStrikeResistance();
		this.footStrikeResistance = parents[Randomizer.getPosInt(parents.length)].getFootStrikeResistance();
		this.arrowResistance = parents[Randomizer.getPosInt(parents.length)].getArrowResistance();
		this.setTotalResistance();	
		
		this.kind = parents[0].getKind();
		this.gender = gender;
		
		this.setDerivatedAttributes();
		this.setMiscelaneousAttributes();
		
	}
	
	private void setDamageResistance(){
		int mult = 10;
		if(Randomizer.getPosInt(100) < 25){
			mult = Randomizer.getPostInt(50, 3, 100, true);
		}
		this.handStrikeDamage = Randomizer.getFloat()*mult/10;
		this.handStrikeResistance = Randomizer.getFloat()*mult;
		mult = 10;
		if(Randomizer.getPosInt(100) < 25){
			mult = Randomizer.getPostInt(50, 3, 100, true);
		}
		this.footStrikeDamage = Randomizer.getFloat()*mult/10 + 10 ;
		this.footStrikeResistance = Randomizer.getFloat()*mult;
		mult = 10;
		if(Randomizer.getPosInt(100) < 25){
			mult = Randomizer.getPostInt(50, 3, 100, true);
		}
		this.arrowResistance = Randomizer.getFloat()*mult;
		this.setTotalResistance();
	}
	private void setTotalResistance(){
		float a = handStrikeResistance;
		float b = footStrikeResistance;
		float c = arrowResistance;
		float d = 100 *(a+b+c) - (a*a + b*b + c*c);
		float e = a+b+c;
		if(e == 300){
			this.totalResistance = 100;
		}
		else{
			this.totalResistance =  d/(300 - e);
		}
	}
	private void setDerivatedAttributes(){
		this.stamina = (handStrikeResistance + footStrikeResistance)/2;
		this.maxSpeed = 6 * ((footStrikeDamage-10)*10 + 2 * footStrikeResistance + stamina)/4;
	}
	private void setMiscelaneousAttributes(){
		this.beauty = Randomizer.getFloat()*10;
		this.sexualAttraction = Randomizer.getFloat()*10;
		this.intelligence = Randomizer.getFloat()*10;
	}
	
	public void fit(){
		float a = handStrikeDamage* 10;
		float b = (footStrikeDamage - 10) *10;
		float d = 100 *(a+b) - (a*a + b*b );
		float e = a+b;
		float total;
		if(e == 200){
			total = 100;
		}else{
			total =  d/(200 - e);
		}
		float timed = 0;
		if(time > 3000){
			timed = 100;
		}
		else{
			timed = time/300;
		}
		this.score = (this.totalResistance + total + timed)/3; // aqui balimos berga
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

	public void mutate(){
		switch(Randomizer.getPosInt(5)){
			case 1:{
				this.handStrikeDamage = Randomizer.getFloat(50, 100);
			}
			case 2:{
				this.footStrikeDamage = Randomizer.getFloat(50, 100);
			}
			case 3:{
				this.handStrikeResistance = Randomizer.getFloat(50, 100);
			}
			case 4:{
				this.footStrikeResistance = Randomizer.getFloat(50, 100);
			}
			case 5:{
				this.arrowResistance = Randomizer.getFloat(50, 100);
			}
		}
		this.setTotalResistance();
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

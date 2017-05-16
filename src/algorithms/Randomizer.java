package algorithms;

import java.util.Random;

public class Randomizer {
	
	private static Random random;
	
	public static int getInt(){
		return getRandomizer().nextInt();
	}
	public static int getPosInt(){
		return Math.abs(getRandomizer().nextInt());
	}
	public static int getPosInt(int max){
		return Math.abs(getRandomizer().nextInt(max));
	}
	public static int getPosInt(int min, int max){
		return Math.abs(getRandomizer().nextInt(max-min) + min);
	}
	public static int getPostInt(int min, int density, int max, boolean up){
		int num;
		if(up){
			num = min;
			for(int i = 0; i < density; i++){
				num = getPosInt(num, max);
				if(num == max){
					break;
				}
			}
		}
		else{
			num = max;
			for(int i = 0; i < density; i++){
				num = getPosInt(min, num);
				if(num == min){
					break;
				}
			}
		}
		return num;
	}
	
	public static float getFloat(){
		return getRandomizer().nextFloat();
	}

	public static float getFloat(int min, int max){
		int num = getPosInt(min, max);
		float add = getFloat();
		if(num + add > max){
			return num;
		}
		else{
			return num + add;
		}
	}
	
	public static String getRandomGender(int ratio){
		String gender = ((Math.abs(getRandomizer().nextInt()%ratio)) == 0) ? "female" : "male";
		return gender;
	}	
	
	private static Random getRandomizer(){
		if(random == null){
			random = new Random();
			random.setSeed(System.currentTimeMillis());
		}
		return random;
	}
}

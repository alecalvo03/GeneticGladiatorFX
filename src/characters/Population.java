package characters;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Population {
	
	private String kind;
	private int generation;
	private int size;
	private boolean victory;
	private int mutations;
	private Gladiator[] gladiators;
	
	public Population(String kind, int generation, int size){
		this.kind = kind;
		this.generation = generation;
		this.size = size;
		this.victory = false;
		this.mutations = 0;
	}	
	
	public void addMutations(int amount){
		this.mutations += amount;
	}
	
	public Element elementGeneration(){
		
		Element victory = DocumentHelper.createElement("victory");
		victory.setText(String.valueOf(this.isVictory()));
		
		Element mutations = DocumentHelper.createElement("mutations");
		mutations.setText(String.valueOf(this.getMutations()));
		
		Element average = DocumentHelper.createElement("average");
		average.setText(String.valueOf(this.getAverageFitness()));
		
		Element generation = DocumentHelper.createElement("generation");
		generation.addAttribute("id", String.valueOf(this.getGeneration()));
		
		generation.add(victory);
		generation.add(mutations);
		generation.add(average);
		
		return generation;
	}
	public Element elementPopulation(){
		
		Element kind = DocumentHelper.createElement("kind");
		kind.setText(this.getKind());
		
		Element size = DocumentHelper.createElement("size");
		size.setText(String.valueOf(this.getSize()));
		
		Element generations = DocumentHelper.createElement("generations");
		
		Element population = DocumentHelper.createElement("population");
		population.addAttribute("id", this.getKind());
		
		population.add(kind);
		population.add(size);
		population.add(generations);
		
		return population;
		
	}
	
	public float getAverageFitness(){
		float average = 0;
		for(int i = 0; i < gladiators.length; i++){
			average += gladiators[i].getScore();
		}
		return average/gladiators.length;
	}
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getGeneration() {
		return generation;
	}
	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean isVictory() {
		return victory;
	}
	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	public int getMutations() {
		return mutations;
	}
	public void setMutations(int mutations) {
		this.mutations = mutations;
	}

	public Gladiator[] getGladiators() {
		return gladiators;
	}
	public void setGladiators(Gladiator[] gladiators) {
		this.gladiators = gladiators;
	}

}

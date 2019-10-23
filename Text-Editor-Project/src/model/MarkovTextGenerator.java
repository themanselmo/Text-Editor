package model;

public interface MarkovTextGenerator {

	public void train(String text);
	
	
	public String generateText(int numWords);
	
	
	public void reTrain(String text);
}

package model;

import java.util.Random;

public class MarkovTextGeneratorLL implements MarkovTextGenerator{

	private DoublyLinkedList MasterList;
	private String starter;
	private boolean trained;
	private Random rnGenerator;
	
	public MarkovTextGeneratorLL(Random generator) {
		MasterList = new DoublyLinkedList();
		starter = "";
		rnGenerator = generator;
		trained = false;
	}
	
	@Override
	public void train(String text) {
		if(trained == true) return;
		String words[] = text.split("[ ]+");
		starter = words[0];
		
		
	}
	@Override
	public String generateText(int numWords) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void reTrain(String text) {
		// TODO Auto-generated method stub
		
	}
	
	

}

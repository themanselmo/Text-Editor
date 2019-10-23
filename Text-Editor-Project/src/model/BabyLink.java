package model;

public class BabyLink {

	private String word;
	private BabyLink next;
	private BabyLink previous;
	
	public BabyLink(String word) {
		this.word = word;
		this.next = null;
		this.previous = null;
	}
	
	public void displayLink() {
		System.out.println(word + " ");
	}

	@Override
	public String toString() {
		return "BabyLink [word=" + word + ", next=" + next + ", previous=" + previous + "]";
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public BabyLink getNext() {
		return next;
	}

	public void setNext(BabyLink next) {
		this.next = next;
	}

	public BabyLink getPrevious() {
		return previous;
	}

	public void setPrevious(BabyLink previous) {
		this.previous = previous;
	}
	
	
}

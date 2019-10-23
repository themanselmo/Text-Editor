package model;

public class MasterLink {

	String word;
	BabyLinkList babyList;
	MasterLink next;
	MasterLink previous;

	public MasterLink(String word) {
		this.word = word;
		this.babyList = new BabyLinkList();
		this.next = null;
		this.previous = null;
	}

	public void displayMasterLink() {
		System.out.println("{" + word + "}");
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public BabyLinkList getBabyList() {
		return babyList;
	}

	public void setBabyList(BabyLinkList babyList) {
		this.babyList = babyList;
	}

	public MasterLink getNext() {
		return next;
	}

	public void setNext(MasterLink next) {
		this.next = next;
	}

	public MasterLink getPrevious() {
		return previous;
	}

	public void setPrevious(MasterLink previous) {
		this.previous = previous;
	}

}

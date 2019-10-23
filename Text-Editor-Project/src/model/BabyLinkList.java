package model;

public class BabyLinkList {
	private BabyLink first;
	private BabyLink last;
	
	public BabyLinkList() {
		this.first = null;
		this.last = null;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void insertFirst(String word) {
		BabyLink newLink = new BabyLink(word);
		if (isEmpty()) {
			last = newLink;
		} else {
			first.setPrevious(newLink);
		}
		newLink.setNext(first);
		first = newLink;
	}
	
	public void insertLast(String word) {
		BabyLink newLink = new BabyLink(word);
		if(isEmpty()) {
			first = newLink;
		} else {
			last.setNext(newLink);
			newLink.setPrevious(last);
		}
		last = newLink;
	}
	
	public BabyLink deleteLast() {
		BabyLink temp = last;
		if(first.getNext() == null) {
			first = null;
		} else {
			last.getPrevious().setNext(null); 
		}
		last = last.getPrevious();
		return temp;
	}
	
	
}

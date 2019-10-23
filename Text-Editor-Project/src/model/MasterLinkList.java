package model;

public class MasterLinkList {
	MasterLink first;
	MasterLink last;
	int nElems;
	
	public MasterLinkList() {
		this.first = null;
		this.last = null;
	}
	
	public boolean contains(String word) {
		while(first.getNext() != null) {
			
			if(first.getNext().getWord().equals(word)) {
				return true;
			}
		}
		return false;
	}
	
	public void display() {
		MasterLink current = first; 
		while(current != null) {
		current.displayMasterLink();
		current = current.next;
		}
		System.out.println();
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void insertFirst(String word) {
		MasterLink newLink = new MasterLink(word);
		nElems++;
		if (isEmpty()) {
			last = newLink;
		} else {
			first.setPrevious(newLink);
		}
		newLink.setNext(first);
		first = newLink;
	}
	
	public void insertLast(String word) {
		MasterLink newLink = new MasterLink(word);
		nElems++;
		if(isEmpty()) {
			first = newLink;
		} else {
			last.setNext(newLink);
			newLink.setPrevious(last);
		}
		last = newLink;
	}
	
	public MasterLink deleteLast() {
		MasterLink temp = last;
		nElems--;
		if(first.getNext() == null) {
			first = null;
		} else {
			last.getPrevious().setNext(null); 
		}
		last = last.getPrevious();
		return temp;
	}

	public MasterLink getFirst() {
		return first;
	}

	public MasterLink getLast() {
		return last;
	}
	
	
}

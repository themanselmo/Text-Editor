package model;
//@TODO finish transferring the links to masterLinks
public class DoublyLinkedList {
	private MasterLink first;
	private MasterLink last;

	public DoublyLinkedList() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void displayForward() {
		MasterLink current = first;
		while(current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}
	
	public void displayBackward() {
		MasterLink current = last;
		while(current != null) {
			current.displayLink();
			current = current.previous;
		}
	}
	
	public MasterLink deleteLast() {
		MasterLink temp = last;
		if (first.next == null) { // one item in the list
			first = null;
		} else {
			last.previous.next = null;
		}
		last = last.previous;
		return temp;
	}

	public MasterLink deleteFirst() { // assume not empty
		MasterLink temp = first;
		if (first.next == null) { // only one item in the list
			last = null;
		} else { // more than one item
			first.next.previous = null;
		}
		first = first.next;
		return temp;
	}

	public MasterLink deleteKey(double key) {
		MasterLink current = first;
		while (current.dData != key) {
			current = current.next;
			if (current == null) {
				return null;
			}
		}
		// shallow copy ; same address
		MasterLink match = current;
		
		if (match == first) {
			first = match.next;
		} else {
			match.previous.next = match.next;
		}

		if (match == last) {
			last = match.previous;
		} else {
			match.next.previous = match.previous;
		}
		return match;
	}

	public void insertFirst(double dData) {
		Link newLink = new Link(dData);
		if (isEmpty()) {
			last = newLink;
		} else {
			first.previous = newLink;
		}
		newLink.next = first;
		first = newLink;
	}

	public void insertLast(double dData) {
		Link newLink = new Link(dData);
		if (isEmpty()) {
			first = newLink;
		} else {
			last.next = newLink;
			newLink.previous = last;
		}
		last = newLink;
	}

	public boolean insertBefore(double key, double dData) {
		Link newLink = new Link(dData);
		Link current = first;
		while(current.dData != key) {
			current = current.next;
			if(current == null) {
				return false;
			}
		}
		// match found
		if(current == first) {
			newLink.next = first;
			first.previous = newLink;
			first = newLink;
		} else {
			newLink.next = current;
			current.previous.next = newLink;
			newLink.previous = current.previous;
			current.previous = newLink;
		}
		return true;
		
	}
	
	public boolean insertAfter(double key, double dData) {
		Link newLink = new Link(dData);
		Link current = first;
		while (current.dData != key) {
			current = current.next;
			if (current == null) {
				return false;
			}
		}
		if (current == last) {
			last = newLink;
		} else {
			newLink.next = current.next;
			current.next.previous = newLink;
		}
		newLink.previous = current;
		current.next = newLink;
		return true;
	}

}
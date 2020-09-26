/* Name: Christina Liu
   NetID: cliu61	
   Class: CSC 172  
   Assignment: Project 1
 */
//Class containing all methods needed for queue implementation
public class Stack<Item> {
	//Variable for first item in stack
	public Node<Item> first;
	//Constructor to make a stack
	public Stack() {
		first = new Node<Item>();
	}	
	//Add an item to the front of the stack
	public void push(Item x) {
		
			Node<Item> temp = new Node<Item>();
		
			temp.data = x;
			temp.next = first.next;
			first.next = temp;
		
	}
	//Remove an item from the front of the stack
	public Item pop() {
		
		if(isEmpty() == true) {
			return null;
		}
		else {
			Node<Item> temp = new Node<Item>();
			temp.data = first.next.data;
			first.next = first.next.next;
			return temp.data;
		}	
	}
	
	//Return value of first item in the stack
		public Item peek() {
			return first.next.data;
		}
	
		//Use print method from Node to print 
	public void print() {
		first.next.print();
	}
	//Boolean method to check if stack is empty
		public boolean isEmpty() {
			if(first.next == null) {
				return true;
			}
			return false;
		}
}

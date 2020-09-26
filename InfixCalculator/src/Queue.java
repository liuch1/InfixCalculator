/* Name: Christina Liu
   NetID: cliu61	
   Class: CSC 172  
   Assignment: Project 1
 */

//Class containing all the methods needed for queue implementation
public class Queue<Item>{
	//Variables for first and last item of queue
	public DoubleNode<Item> first;
	public DoubleNode<Item> last;
	//Constructor for Queue
	public Queue() {
		first = new DoubleNode<Item>();
		last = new DoubleNode<Item>();
		first.next = last;
		first.previous = null;
		last.next = null;
		last.previous = first;
	}
	//Enqueue item, add item to end of queue
	public void enqueue(Item x) {
		
			DoubleNode<Item> temp = new DoubleNode<Item>();
			temp.data = x;
			temp.previous = last.previous;
			temp.next = last;
			(temp.previous).next = temp;
			(temp.next).previous = temp;
	}
	//Dequeue an item, remove item from end of queue
	public Item dequeue() {
		DoubleNode<Item> temp = new DoubleNode<Item>();
		if(isEmpty() == true) {
			return null;
		}
		else {
			temp.data = first.next.data; 
			first.next = first.next.next; //change next item into item after
			first.next.previous = first; //change previous item into current 
			return temp.data;
		}
	}
	//Print queue using print method created previously with DoubleNode
		public void print() {
			first.next.print();
		}

	//Boolean method to check if queue is empty
	public boolean isEmpty() {
		if(first.next == last) {
			return true;
		}
		return false;
	}
	//Method that returns first item in queue
		public Item peek() {
			if(isEmpty() == true) {
				return null;
			}
			else {
				return first.next.data;
			}
		}
}

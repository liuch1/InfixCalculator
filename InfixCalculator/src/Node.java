/* Name: Christina Liu
   NetID: cliu61	
   Class: CSC 172  
   Assignment: Project 1
 */
//Class to denote node for Stack
public class Node<Item> {
	
	//Variables to denote the data item in the node and its link to the next node
	public Item data;
	public Node<Item> next;
	//Constructor for node
	public Node() {
		data = null;
		next = null;
	}
	//Method for printing node
	public void print() {
		if(this == null || this.data == null) {
			return;
		}
		else{
			System.out.print(data);
			if(this.next != null) {
				this.next.print();
			}
		}	
	}

}

/* Name: Christina Liu
   NetID: cliu61	
   Class: CSC 172  
   Assignment: Project 1
 */
//Class to denote double node for queue
public class DoubleNode<Item> {
	//Variables denoting the data item in the node along with the link to the next and previous node
	public Item data;
	public DoubleNode<Item> next;
	public DoubleNode<Item> previous;
	//Constructor for DoubleNode
	public DoubleNode() {
		data = null;
		next = null;
		previous = null;
	}
	//Method for printing double node
	public void print() {
		if(this == null || this.data == null) {
			return;
		}
		else{
			System.out.print(data);
			this.next.print();
		}	
	}
}


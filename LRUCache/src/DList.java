
public class DList {
	DllNode head; 
	DllNode end;
	
	public DList(){
		head = null;
		end = null;
	}
	
	public synchronized void add(DllNode node){	
		if(node == null) return;
		
		//empty list
		if (this.head == null){
			this.head = node;
			node.setNext(null);
			node.setPrev(null);
			this.end = head;
			return;
		}
	
		DllNode temp = head;
		head = node;
		head.setNext(temp);
		temp.setPrev(head);
	}

	public DllNode removeFromLast(){
		if(end== null) return null;
		synchronized(end){
			DllNode temp = end;
			end = temp.getPrev();
			if(end !=null){
				end.setNext(null);
			}
			return temp;
		}
	}
	
	public void MoveAtStart(DllNode node){
		if(node == null) return;
		
		synchronized(node){
			//Node is already at the start 
			if(node == head) return;
			
			//Node is at the end
			if(node==end){
				removeFromLast();
				node.setPromotionTime(new Date());
				add(node);
			}
			
			//Node is in middle
			node.getPrev().setNext(node.getNext());
			node.getNext().setPrev(node.getPrev());
			add(node);
		}
	}
}

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LRUCache implements Runnable {
	int capacity;
	ConcurrentMap<Integer,DllNode> hashMap;
	Queue<DllNode> mruQueue;
	DList dlist;
	
	public LRUCache(int capacity){
		this.capacity = capacity;
		this.hashMap = new ConcurrentHashMap<Integer, DllNode>();
		this.dlist =  new DList();
		mruQueue = new LinkedList<DllNode>();
		//start a new thread to act on the queue 
	}
	
	public int get(int key){
		if(this.hashMap.containsKey(key)){
			DllNode node = this.hashMap.get(key);
			if(node.getKey() == key){
				//Move this node to start of the list
				if(node.isPromotionExpired()){
					dlist.MoveAtStart(node);
					//mruQueue.add(node);
				}
				return node.getValue(); 
			}
		}
		return -1;
	}
	
	public void put(int key, int value){
		if(this.hashMap.size() >= this.capacity){ //DLL is full
			DllNode deletedNode = dlist.removeFromLast();
			this.hashMap.remove(deletedNode.getKey());
			deletedNode = null;
		}
		DllNode node = new DllNode(key, value);
		this.hashMap.put(key, node);
		
		//add node at the start of the list 
		dlist.add(node);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(mruQueue.size() > 0){
			DllNode node = mruQueue.remove();
			dlist.MoveAtStart(node);
		}		
	}
}

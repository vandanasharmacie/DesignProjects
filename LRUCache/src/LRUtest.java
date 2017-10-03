import java.io.Console;

public class LRUtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCache lru = new LRUCache(1);
		Thread lruThread = new Thread(lru);
		lruThread.start();
		
		lru.put(1, 3);
		int val = lru.get(1);
		System.out.println(val);
		lru.put(2,5);
		val = lru.get(2);
		System.out.println(val);
		val = lru.get(1);
		System.out.println(val);
	}

}

import java.util.Date;



public class DllNode {
	private int key;
	private int value;
	private DllNode next;
	private DllNode prev;
	private long promotionTime;
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public DllNode getNext() {
		return next;
	}

	public void setNext(DllNode next) {
		this.next = next;
	}

	public DllNode getPrev() {
		return prev;
	}

	public void setPrev(DllNode prev) {
		this.prev = prev;
	}
	
	public long getPromotionTime() {
		return promotionTime;
	}

	public void setPromotionTime(long promotionTime) {
		this.promotionTime = promotionTime;
	}

	public boolean isPromotionExpired(){
		long currentMSeconds = System.currentTimeMillis();
		if((currentMSeconds - promotionTime) >= LRUConstants.PROMOTION_EXPIRY_TIME)
			return true;
		return false;
	}
	
	public DllNode(int key, int value){
		this.key = key;
		this.value = value;
		this.next = null;
		this.prev = null;
		this.promotionTime = System.currentTimeMillis();
	}
}

package shortPathAlgo;
/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/
public class Queue {
	
	Vertex[] Q;
	int size;
	int capacity;
	int front = 0; 
	int back = 0;
	
	// [front ---------------- back] 
	
	public Queue(int n) {
		capacity = n;
		Q = new Vertex[capacity];
		size = 0;
	}//end constructor
	
	// checks if queue is empty
	public boolean isEmpty(){
	      return (front == back);
	}
	
	public boolean isFull(){
	      return (size == capacity);
	}
	
	// enqueue (insert at front)
	public void enqueue(Vertex v) {  // h->3->2->1
		if (isFull()) {
			throw new IllegalArgumentException("Queue Overflow!");
		} else {
			Q[back] = v;
			back = (back+1) % capacity;
			size++;
		}
	}
	
	// dequeue (extract and remove from back)
	Vertex dequeue() {
		Vertex v;
		if (isEmpty()) {
			throw new IllegalArgumentException("Queue Underflow!");
		} else {
			v = Q[front];
			if (++front == capacity) {
				front = 0;
			} 
			return v;
		}
	}
	
	Vertex peek() {
		   return Q[front];
	}
}

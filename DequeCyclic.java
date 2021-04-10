
/**
 * A Cyclic Block Implementation
 * of a Double Ended Queue (DEQUE)
 *
 */
import CITS2200.Deque;
import CITS2200.Underflow;
import CITS2200.Overflow;


public class DequeCyclic implements Deque<Object>{
	/**
	 * member variables of class DequeCyclic
	 * queue is an array which we will use as a dequeue
	 * size is the total size of the dequeue
	 * front is the start handle of the dequeue
	 * rear is the end handle of the dequeue
	 * count is number of objects in the dequeue
	 */
	Object queue [];
	int size;
	int front;
	int rear;
	int count;
	/**
	 * Class Constructor
	 * when the dequeue is initialized it is empty
	 * therefore the size and the count are 0
	 * as there is no object in dequeue the handles are initialized to 
	 * indices -1 as it does not exist.
	 *
	 */
	public DequeCyclic(int s)
	{
		queue = new Object[s];
		size = s;
		front = -1;
		rear = -1;
		count = 0;
			}
	/**
	 * This method creates an empty
	 * dequeue of size s
	 */
	
	/**
	 * This method returns true iff the dequeue is empty
	 * else it returns false
	 */
	public boolean isEmpty()
	{
		if(count == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * This method returns true iff the dequeue is full
	 * else it returns false 
	 *
	 */
	public boolean isFull()
	{
		if(count == size)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * This method adds object c as the left-most object in dequeue,
	 * or throws an OverFlow exception if the dequeue is full
	 */
	public void pushLeft(Object c)throws Overflow
	{
		if(isEmpty() == true)
		{
			front = 0;
			queue[front] = c;
			rear = rear + 1;
			count = count + 1;
			return;	
		}
		if( isFull() == true)
		{
			 throw new Overflow("Queue is overflowed");
		}
		if(front == 0)
		{
			front = size - 1;
			queue[front] = c;
			count = count + 1;
			return;
		}
		if (front != 0 && rear != front -1)
		{
			front = front - 1;
			queue[front] = c;
			count = count + 1;
			return;
			
		}
	}
	/**
	 * This method adds object c as the right-most object in dequeue,
	 * or throws an OverFlow exception if the dequeue is full
	 */
	public void pushRight(Object c)throws Overflow
	{
		if(isEmpty() == true)
		{
			front = 0;
			queue[front] = c;
			rear = rear + 1;
			count = count + 1;
			return;	
		}
		if(isFull() == true)
		{
			throw new Overflow("Queue is overflowed");
		}

		if( front != rear + 1 && rear + 1 != size)
		{
			rear = rear + 1;
			queue[rear] = c;
			count = count + 1;
			return;
		}
		if(rear == size - 1 && front != 0)
		{
			rear = 0;
			queue[rear] = c;
			count = count + 1;
			return;
			
		}
	}
	/**
	 * This method returns the left most object in the dequeue 
	 * or throws an UnderFlow exception if the queue is empty
	 */
	public Object peekLeft() throws Underflow
	{
		if(isEmpty() == true)
		{
			throw new Underflow("Queue is underflowed");
		}
		else
		{
			return queue[front]; 
		}
	}
	/**
	 * This method returns the right most object in the dequeue 
	 * or throws an UnderFlow exception if the queue is empty
	 */
	public Object peekRight() throws Underflow
	{
		if(isEmpty() == true)
		{
			throw new Underflow("Queue is underflowed");
		}
		else
		{
			return queue[rear]; 
		}
	}
	/**
	 * This method remove and returns the left most object in the dequeue 
	 * or throws an UnderFlow exception if the queue is empty
	 */
	public Object popLeft() throws Underflow
	{
		if(isEmpty() == true)
		{
			throw new Underflow("Queue is underflowed");
		}
		boolean flag = false;
		Object x = queue[front];
		queue[front] = 0;
		count = count - 1;
		if(front == 0 && rear == 0)
		{
			front = -1;
			rear = -1;
			flag = true;
		}
		if(front == size -1)
		{
			if(rear == size -1)
			{
				rear = 0;
			}
			front = 0;
			flag = true;
		}
		if(front != size-1 && flag != true) 
		{
			front = front + 1;
		}
		return x;
	}
	/**
	 * This method remove and returns the right most object in the dequeue 
	 * or throws an UnderFlow exception if the queue is empty
	 */
	public Object popRight() throws Underflow
	{
		if(isEmpty() == true)
		{
			throw new Underflow("Queue is underflowed");
		}
		Object x = queue[rear];
		queue[rear] = 0;
		count = count - 1;
		if(rear == 0 && front == 0)
		{
			rear = -1;
			front = -1;
		}
		if(rear == 0 && front != 0)
		{
			rear = size -1;
		}
		if(rear > 0 )
		{
			rear = rear - 1;
		}
		return x;
	}
	
	
	
}

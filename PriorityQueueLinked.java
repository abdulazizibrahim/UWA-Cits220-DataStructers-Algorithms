import CITS2200.IllegalValue;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import CITS2200.PriorityQueue;
import CITS2200.Underflow;
public class PriorityQueueLinked<E> implements PriorityQueue<Object>{

	private Link front;
	public PriorityQueueLinked()
	{
		front = null;
	}
	private class Link{
		Object element;
		int priority;
		Link next;
		public Link(Object e, int p, Link n) {
			this.element = e;
			this.priority = p;
			this.next = n;
	
		}
	}
	@Override
	public Object dequeue() throws Underflow {
		// TODO Auto-generated method stub
		if (!isEmpty()) {
				Object temp =  front.element;
				front = front.next;
				return temp;
			} 
			else
			{
				throw new Underflow("Empty Queue");
			}
	}

	@Override
	public void enqueue(Object e, int p) throws IllegalValue {
		// TODO Auto-generated method stub
		if(p < 0)
		{
			throw new IllegalValue("p is  negative");
		}
		if (isEmpty() || p > front.priority) {
				front = new Link(e, p, front);
			} else {
				Link l = front;
				while (l.next != null && l.next.priority >= p) {
						l = l.next;
				}
				l.next = new Link(e, p, l.next);
			}
	}

	@Override
	public Object examine() throws Underflow {
		// TODO Auto-generated method stub
		if (!isEmpty()) {
			return front.element;
			} 
			else
			{
				throw new Underflow("Empty Queue");
			}
				
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return front == null;
	}
	private class BackingQueue implements Iterator<Object>{
		private Link current;
		public BackingQueue()
		{
			current = front;		
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(current.next != null)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}

		@Override
		public Object next() throws OutOfBounds {
			// TODO Auto-generated method stub
			
			if(current == null)
			{
				throw new OutOfBounds("No next element in Queue");
			}
			Object temp = current.element;
			current = current.next;
			return temp;
		}
		
		
	}
	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return new BackingQueue();
	}

}

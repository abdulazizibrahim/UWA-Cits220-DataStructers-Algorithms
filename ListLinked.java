import CITS2200.Link;
import CITS2200.List;
import CITS2200.OutOfBounds;
import CITS2200.WindowLinked;
 /**
 * Linked implementation of a List DataStructure
 */
public class ListLinked implements List {
	 
	private Link before;
	private Link after;
	public ListLinked () {
	after = new Link(null, null);
	before = new Link(null, after);
	}
	

	@Override
	//: Initializes w to the after last position.
	public void afterLast(WindowLinked w) {
		w.link = after;
	}

	@Override
	//: Initializes w to the before first position.
	public void beforeFirst(WindowLinked w) {
		w.link = before;
	}

	@Override
	//Throws an exception if w is in the before first or after last position,
	//otherwise deletes and returns the element under w, and places w over the next
	//element.
	public Object delete(WindowLinked w) throws OutOfBounds {
		if(w.link == before || w.link == after)
		{
			throw new OutOfBounds ("deleting start or end of list");
		}
		Link w1 = w.link.successor;
		Object x = w.link.item;
		w.link.item = w1.item;
		w.link.successor = w1.successor;
		w1.successor = null;
		w1 = null;
		return x;
	}

	@Override
	//Throws an exception if w is in the before first or after last position,
	//otherwise returns the element under w
	public Object examine(WindowLinked w) throws OutOfBounds {
		if(w.link == before || w.link == after)
		{
			throw new OutOfBounds ("examining start or end of list");
		}
		return w.link.item;
	}

	@Override
	//Throws an exception if w is over the after last position, otherwise an extra element e is added to the list after w.
	public void insertAfter(Object e, WindowLinked w) throws OutOfBounds {
		if(w.link == after)
		{
			throw new OutOfBounds ("w is in after last position can not insert after last");
		}
		Link newInsert = new Link(e, w.link.successor);
		w.link.successor = newInsert;
	}

	@Override
	// Throws an exception if w is over the before first position,
	//otherwise an extra element e is added to the list before w
	public void insertBefore(Object e, WindowLinked w) throws OutOfBounds {
		if (!isBeforeFirst(w)) {
			w.link.successor = new Link(w.link.item, w.link.successor);
			if (isAfterLast(w)) after = w.link.successor;
			w.link.item = e;
			w.link = w.link.successor;
			}
			else throw new OutOfBounds ("inserting before start of list");
					
	}

	@Override
	//  returns true if w is over the after last position.
	public boolean isAfterLast(WindowLinked w) {
		if(w.link == after)
		{
			return true;
		}
		return false;
	}

	@Override
	// returns true if w is over before first position.
	public boolean isBeforeFirst(WindowLinked w) {
		if(w.link == before)
		{
			return true;
		}
		return false;
	}

	@Override
	//: Returns true if the list is empty
	public boolean isEmpty() {
		return before.successor == after;
	}

	@Override
	// Throws an exception if w is over the after last position, otherwise
	//moves w to the next window position.
	public void next(WindowLinked w) throws OutOfBounds {
		if (!isAfterLast(w)) w.link = w.link.successor;
		else
		throw new OutOfBounds("Calling next after list end.");

	}

	@Override
	//Throws an exception if w over is the before first position, otherwise
	//moves w to the previous window position.
	public void previous(WindowLinked w) throws OutOfBounds {
		if (!isBeforeFirst(w)) {
			Link current = before.successor;
			Link previous = before;
			while (current != w.link) {
				previous = current;
				current = current.successor;
			}
			w.link = previous;
		}
		else throw new OutOfBounds ("Calling previous before start of list.");
	}

	@Override
	//Throws an exception if w is in the before first or after last position,
	//otherwise replaces the element under w with e and returns the old element
	public Object replace(Object e, WindowLinked w) throws OutOfBounds {
		if(w.link == before || w.link == after)
		{
			throw new OutOfBounds ("can not replace element in before first or after last position");
		}
		Object x = w.link.item;
		w.link.item = e;
		return x;
	}
	

}

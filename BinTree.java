import CITS2200.BinaryTree;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import CITS2200.Underflow;

import java.util.*;  
public class BinTree extends BinaryTree<Object>{
	public BinTree()
	{
		super();
	}
	public BinTree(Object item, BinaryTree<Object> b1, BinaryTree<Object> b2)
	{
		super(item, b1, b2);
	}
	@Override
	public boolean equals(Object e) {
		// TODO Auto-generated method stub
		BinTree bin = (BinTree) e;
		if(e == null)
		{
			return false;
		}
		if(!(e instanceof BinTree))
		{
			return false;
		}
		if(bin.isEmpty() && this.isEmpty())
		{
			return true;
		}
		if(bin.getItem() != this.getItem())
		{
			return false;
		}
		if((bin.isEmpty() && !(this.isEmpty())) || (!(bin.isEmpty()) && (this.isEmpty())))
		{
			return false;
		}
		if((bin.getLeft() != null && this.getLeft() == null) ||(bin.getLeft() == null && this.getLeft() != null))
		{
			return false;
		}
		if((bin.getRight() != null && this.getRight() == null) ||(bin.getRight() == null && this.getRight() == null))
		{
			return false;
		}
		if(bin.getLeft() != null && this.getLeft() != null)
		{
			equals(bin.getLeft());
		}
		if(bin.getRight() != null && this.getRight() != null)
		{
			equals(bin.getRight());
		}
		return true;
	}
	private class BTree implements Iterator<Object>{
		
		BinTree obj;
		 Queue<BinaryTree<Object>> que = new LinkedList<>();
		BTree(BinTree t1)
		{
			obj = t1;
			que.add(obj);
			
		}
		@Override
		public boolean hasNext() throws Underflow {
			// TODO Auto-generated method stub
			if(isEmpty())
			{
				throw new Underflow("tree shit bro");
			}
			if(getLeft() == null && getRight() == null)
			{
				return false;
			}
			return true;
		}

		@Override
		public Object next() throws Underflow {
			// TODO Auto-generated method stub
			if(hasNext() == false)
			{
				throw new OutOfBounds("tree is empty"); 
			}
			BinTree temp = (BinTree) que.remove();
			if(temp.isEmpty() == true)
			{
				//return temp.getItem();
				throw new Underflow("tree is empty"); 
			}
			if(temp.getLeft() != null)
			{
				que.add(temp.getLeft());
			}
			if(temp.getRight() != null)
			{
				que.add(temp.getRight());
			}
			return temp.getItem();
		}
	}
	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		
		return new BTree(this);
	}
	
	
}

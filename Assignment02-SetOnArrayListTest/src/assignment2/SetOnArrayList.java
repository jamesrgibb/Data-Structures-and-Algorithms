package assignment2;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import components.set.AbstractSet;




/**
 * {@code Set<E>} represented as a {@code java.util.ArrayList<E>} with
 * implementation of primary methods.
 * 
 * @author James Gibb
 *
 * @param <E> type of element
 */
public class SetOnArrayList<E> extends AbstractSet<E>{
	/*
	 * Private members
	 */
	/**
	 * Underlying ArrayList representation
	 */
	private List<E> rep;

	
	// ----------------------------------------------------------------
	/**
	 * No-argument constructor; builds an empty set.
	 * 
	 */
	public SetOnArrayList() {
		rep = new ArrayList<E>();
		
	}

	@Override
	public void add(E x) {
		assert x != null : "Violation of x is not null";
		if(!rep.contains(x)) {
			rep.add(x);
		}
	}

	@Override
	public boolean contains(E x) {
		assert x != null : "Violation of x is not null";
		for(int i=0; i<rep.size(); i++) {
			if(x.equals(rep.get(i))) 
				return true;
		}
		
		return false; // placeholder
	}

	@Override
	public void remove(E x) {
		assert x != null : "Violation of x is not null";
		assert this.contains(x) : "Violation of x is in this";
		if(rep.contains(x)) {
			rep.remove(x);
		}
	}

	@Override
	public int size() {
		return rep.size();

	}
	@Override
	public void clear() {
		rep.clear();
	}
	// ----------------------------------------------------------------

	/*
	 * Already implemented for you
	 */
	@Override
	public Iterator<E> iterator() {
		return this.rep.iterator();
	}

}

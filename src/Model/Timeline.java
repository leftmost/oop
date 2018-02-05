package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Timeline implements Iterable<Esperienza>{
	
	List<Esperienza> timeline;
	
	public Timeline() {
		timeline=new ArrayList<Esperienza>();
	}
	
	public boolean add(Esperienza e) {
		return timeline.add(e);
	}
	
	public boolean remove(Esperienza e) {
		return timeline.remove(e);
	}
	
	@Override
	public String toString() {
		return "Timeline [timeline=" + timeline + "]";
	}

	//restituisce ultimo oggetto esperienza
	public Esperienza lastExp() {
		return timeline.get(0);
	}
	

	@Override
	public Iterator<Esperienza> iterator() {
		return this.timeline.iterator();
	}	
	
}

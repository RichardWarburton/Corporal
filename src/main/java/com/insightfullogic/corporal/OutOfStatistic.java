package com.insightfullogic.corporal;

public class OutOfStatistic {

	private String name;
	private long count;
	private long outOf;
	
	public OutOfStatistic(String name) {
		this.count = 0;
		this.outOf = 0;
		this.name = name;
	}
	
	public void incrementOutOf() {
		outOf++;
	}
	
	public void increment() {
		count++;
		outOf++;
	}
	
	@Override
	public String toString() {
		return getName() + ": "+count +"/" + outOf;
	}

	public long getCount() {
		return count;
	}

	public long getOutOf() {
		return outOf;
	}

	public String getName() {
		return name;
	}
	
}

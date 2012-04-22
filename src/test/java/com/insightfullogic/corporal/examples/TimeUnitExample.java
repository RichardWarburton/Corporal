package com.insightfullogic.corporal.examples;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;

import java.util.concurrent.TimeUnit;

public class TimeUnitExample {

	public void staticImport() {
		System.out.println(DAYS);
		System.out.println(DAYS);
		System.out.println(HOURS);
	}
	
	public void normalImport() {
		System.out.println(TimeUnit.DAYS);
		System.out.println(TimeUnit.DAYS);
		System.out.println(TimeUnit.HOURS);
	}
	
}

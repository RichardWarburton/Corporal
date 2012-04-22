package com.insightfullogic.corporal.threeten;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.insightfullogic.corporal.OutOfStatistic;
import com.insightfullogic.corporal.Runner;

public class TestThreetenExamples {

	private static final String egDir = "src/test/java/com/insightfullogic/corporal/examples/";
	
	@Test
	public void timeUnits() throws IOException {
		Runner runner = new Runner(new String[]{egDir+"TimeUnitExample.java"}, ThreetenQueries.INSTANCE.getQueries());
		runner.process();
		List<OutOfStatistic> statistics = runner.getStatistics();
		OutOfStatistic result = Iterables.find(statistics, new Predicate<OutOfStatistic>() {
			@Override
			public boolean apply(OutOfStatistic stat) {
				return stat.getName().contains("TimeUnit");
			}
		});
		assertEquals("Count", 6, result.getOutOf());
		assertEquals("Count", 3, result.getCount());
	}
	
}

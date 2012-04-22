package com.insightfullogic.corporal.threeten;

import java.util.Arrays;
import java.util.List;

import com.insightfullogic.corporal.JavaQuery;
import com.insightfullogic.corporal.QueryModule;

public enum ThreetenQueries implements QueryModule {

	INSTANCE;

	@Override
	public List<JavaQuery> getQueries() {
		return Arrays.<JavaQuery>asList(new TimeUnitImports());
	}
	
}

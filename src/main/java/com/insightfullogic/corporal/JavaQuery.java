package com.insightfullogic.corporal;

import org.eclipse.jdt.core.dom.CompilationUnit;

public interface JavaQuery {

	public void processFile(CompilationUnit file);
	
	public OutOfStatistic getStatistic();
	
}

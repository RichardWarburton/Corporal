package com.insightfullogic.corporal.threeten;

import static com.insightfullogic.corporal.EclipseIsTerrible.imports;
import static com.insightfullogic.corporal.EclipseIsTerrible.types;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

import com.google.common.collect.Sets;
import com.insightfullogic.corporal.JavaQuery;
import com.insightfullogic.corporal.OutOfStatistic;

public class TimeUnitImports implements JavaQuery {

	private final OutOfStatistic statistic = new OutOfStatistic("TimeUnit uses via static import");

	private final Set<String> allImports;

	TimeUnitImports() {
		allImports = Sets.newHashSet();
		for (TimeUnit unit : TimeUnit.values()) {
			allImports.add(unit.toString());
		}
	}

	@Override
	public void processFile(CompilationUnit file) {
		boolean importEnum = false;
		final Set<String> names = Sets.newHashSet();
		for (ImportDeclaration decl : imports(file)) {
			String importString = decl.toString();
			if (importString.contains("java.util.concurrent.TimeUnit;")) {
				importEnum = true;
			} else if (importString.contains("java.util.concurrent.TimeUnit.")) {
				String importedElement = importString.substring(importString.lastIndexOf('.') + 1, importString.lastIndexOf(";")).trim();
				if ("*".equals(importedElement)) {
					names.addAll(allImports);
				} else {
					names.add(importedElement);
				}
			}
		}

		if (importEnum || !names.isEmpty()) {
			ASTVisitor visitor = new ASTVisitor() {

				private boolean afterTimeUnit = false;

				@Override
				public boolean visit(SimpleName node) {
					String name = node.toString();
					if ("TimeUnit".equals(name)) {
						afterTimeUnit = true;
					} else {
						if (names.contains(name)) {
							if (afterTimeUnit) {
								statistic.incrementOutOf();
							} else {
								statistic.increment();
							}
						}
						afterTimeUnit = false;
					}
					return super.visit(node);
				}
			};

			for (AbstractTypeDeclaration decl : types(file)) {
				decl.accept(visitor);
			}
		}
	}

	@Override
	public OutOfStatistic getStatistic() {
		return statistic;
	}
}

package com.insightfullogic.corporal;

import java.util.List;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;

/**
 * Class exists because JDT doesn't have generics.
 * 
 * Provides methods that do implicit conversion to genericised types of eclipse values.
 */
@SuppressWarnings("unchecked")
public class EclipseIsTerrible {

	public static List<ImportDeclaration> imports(CompilationUnit unit) {
		return unit.imports();
	}
	
	public static List<AbstractTypeDeclaration> types(CompilationUnit unit) {
		return unit.types();
	}
	
}

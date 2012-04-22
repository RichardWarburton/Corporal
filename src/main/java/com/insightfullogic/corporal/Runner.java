package com.insightfullogic.corporal;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.insightfullogic.corporal.threeten.ThreetenQueries;

public class Runner {

	private final String[] argumentFiles;
	private final List<JavaQuery> queries;

	public static void main(String[] args) throws IOException {
		// Initially hardcoding JSR 310 queries for now.
		List<JavaQuery> queries = ThreetenQueries.INSTANCE.getQueries();
		Runner runner = new Runner(args, queries);
		runner.process();
		runner.printStatistics();
	}
	
	public void printStatistics() {
		System.out.println(getStatistics());
	}
	
	public List<OutOfStatistic> getStatistics() {
		return Lists.newArrayList(transform(queries, new Function<JavaQuery, OutOfStatistic>() {
			@Override
			public OutOfStatistic apply(JavaQuery query) {
				return query.getStatistic();
			}
		}));
	}

	public Runner(String[] files, List<JavaQuery> queries) {
		this.argumentFiles = files;
		this.queries = queries;
	}

	public void process() throws IOException {
		List<File> files = newArrayList(transform(asList(argumentFiles), new Function<String, File>() {
			@Override
			public File apply(String file) {
				File f = new File(file);
				if (!f.exists()) {
					// TODO: runtime fnf exception
					throw new RuntimeException(f.getAbsolutePath()+ " not found");
				}
				return f;
			}
		}));
		
		searchRecursive(files);
	}

	private void searchRecursive(List<File> files) throws IOException {
		for (File file : files) {
			if (file.isDirectory()) {
				searchRecursive(Arrays.asList(file.listFiles()));
			} else if (file.getName().endsWith(".java")) {
				ASTParser parser = ASTParser.newParser(AST.JLS3);
				String contents = Files.toString(file, Charset.defaultCharset());
				parser.setSource(contents.toCharArray());
				CompilationUnit unit = (CompilationUnit) parser.createAST(null);
				for (JavaQuery query : queries) {
					query.processFile(unit);
				}
			}
		}
	}

}

package com.cmsz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * 日志分析的简单样例，请自己重新实现
 */
public class AnalyseSample implements Analyse {

	@Override
	public void doAnalyse(String path) {
		File[] logFiles = new File(path).listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().startsWith("send");
			}
		});
		try (BufferedReader reader = new BufferedReader(new FileReader(logFiles[0]));) {
			String line = reader.readLine();
			String outputFileName = path + (path.endsWith(File.separator) ? "" : File.separator) + "result.txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFileName)));
			writer.write(line);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

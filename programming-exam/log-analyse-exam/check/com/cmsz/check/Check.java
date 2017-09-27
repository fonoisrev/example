package com.cmsz.check;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;

import com.cmsz.Analyse;
import com.cmsz.AnalyseSample;

public class Check {
	
	private static final String rootPath = "usecases";
	private static final Analyse myAnalyse = new AnalyseSample();
	
	public static void main(String[] args) {
		// 1. 执行日志分析
		File root = new File(rootPath);
		doAnalyzes(root);
		
		// 2. 校验日志结果
		File[] files = root.listFiles();
		for (File file: files) {
			if (file.isDirectory()) {
				System.out.println("**************************开始对比"+ file.getName() +"*******************");
				directoryComp(file.getAbsolutePath());
				System.out.println("**************************对比结束"+ file.getName() +"*******************");
			}			
		}		
	}

	private static void doAnalyzes(File root) {
		System.out.println("执行日志文件分析");
		File[] dirs = root.listFiles();
		for(File dir : dirs){
			System.out.println("分析:" + dir.getAbsolutePath());
			long start = System.currentTimeMillis();
			myAnalyse.doAnalyse(dir.getAbsolutePath());
			long end = System.currentTimeMillis();
			System.out.println("耗时：" + (end - start) + "ms");
		}
	}

	public static void directoryComp(String path){
		File[] examFiles = new File(path).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().startsWith("result");
			}
		});
		
		if (examFiles == null) {
			System.err.println("result文件不存在");
			return;
		}
		
		File compareFile = new File(path + File.separator + "compareFileEncode.txt");	
		doDiff(examFiles[0], compareFile);
	}
	
	public static void doDiff(File examFile, File compareEncodeFile) {
		// 获取正确的比对文件的正确错误集合
		HashSet<String> successSet = new HashSet<String>();
		HashSet<String> failSet = new HashSet<String>();

		try {
			//解码比对文件
			System.out.println("--------------------开始解码文件-----------------------");
			String str = Utils.decode(compareEncodeFile);
			BufferedReader compareBr = new BufferedReader(new StringReader(str));
			String line = compareBr.readLine();
			// 读取正确的比对文件，获得两个集合
			if (!("SUCCESS:").equals(line)) {
				System.err.println("解码文件错误，首行不是SUCCESS:");
				return;
			}
			while (!(line = compareBr.readLine()).equals("FAIL:")) {
				successSet.add(line);
			}
			while ((line = compareBr.readLine()) != null) {
				failSet.add(line);
			}
			compareBr.close();
			System.out.println("--------------------解码文件完成-----------------------");
			
			// 获取待测试的比对文件的集合
			BufferedReader examBr = new BufferedReader(new FileReader(examFile));
			HashSet<String> examSucSet = new HashSet<String>();
			HashSet<String> examFailSet = new HashSet<String>();
			String examLine = examBr.readLine();
			if (!("SUCCESS:").equals(examLine)) {
				System.err.println("result文件错误，首行不是SUCCESS:");
				return;
			}
			while (!(examLine = examBr.readLine()).equals("FAIL:")) {
				if (!examSucSet.add(examLine)) {
					System.err.println("result中存在ID重复:" + examLine);
				}
			}
			while ((examLine = examBr.readLine()) != null) {
				if (!examFailSet.add(examLine)) {
					System.err.println("result中存在ID有重复:" + examLine);
				}
			}
			examBr.close();
			
			// 对比集合
			System.err.println("--------------------开始匹配合法序列-----------------------");
			if (successSet.equals(examSucSet)) {
				System.err.println("合法序列匹配成功");
			} else {
				System.err.println("合法序列匹配失败：");
				Utils.compareSet(examSucSet, successSet);
			}
			
			System.err.println("--------------------开始匹配不合法序列-----------------------");
			if (failSet.equals(examFailSet)) {
				System.err.println("不合法序列匹配成功");
			} else {
				System.err.println("不合法序列匹配失败：" );
				Utils.compareSet(examFailSet, failSet);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

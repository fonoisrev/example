package com.cmsz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class AnalyzeWuhang implements Analyse {

	public static ConcurrentHashMap<String, String> sendMap = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<String, String> failMap = new ConcurrentHashMap<>();

	@Override
	public void doAnalyse(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			throw new RuntimeException(path + "目录不存在");
		}
		
		sendMap.clear();
		failMap.clear();

		readSendFiles(dir);
		readReceiveFiles(dir);

	}

	private void readReceiveFiles(File dir) {
		File[] sendfiles = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getName().toString().toLowerCase().contains("receive");
			}
		});
		File outFile = new File(dir.getAbsolutePath() + File.separator +"result.txt");
		if(outFile.exists()){
			outFile.delete();
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));) {
			writer.write("SUCCESS:");
			writer.newLine();
			
			for (File sendfile : sendfiles) {
				try (BufferedReader reader = new BufferedReader(new FileReader(sendfile));) {
					String line = null;
					while ((line = reader.readLine()) != null) {
						String[] context = line.split("#");
						String orderno = context[3].substring(7);
						String status = context[4].substring(7);
						if (status.equals("RECEIVE")) {
							if(sendMap.containsKey(orderno)){
								sendMap.remove(orderno);
								writer.write(orderno);
								writer.newLine();
							}else{
								failMap.putIfAbsent(orderno, status);
							}
						} else if (status.equals("FAIL")) {
							failMap.putIfAbsent(orderno, status);
						}
					}
				} catch (IOException e) {
					System.err.println(sendfile.getName() + "读取错误：" + e.getMessage());
				}
			}
			
			writer.write("FAIL:");
			writer.newLine();
			failMap.forEach(new BiConsumer<String, String>() {

				@Override
				public void accept(String t, String u) {
					try {
						writer.write(t);
						writer.newLine();
					} catch (IOException e) {
						System.err.println(outFile.getName() + "写入错误：" + e.getMessage());
					}
				}
			});
		}catch (IOException e) {
			System.err.println(outFile.getName() + "写入错误：" + e.getMessage());
		}
		

	}

	private void readSendFiles(File dir) {
		File[] sendfiles = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getName().toString().toLowerCase().contains("send");
			}
		});

		for (File sendfile : sendfiles) {
			try (BufferedReader reader = new BufferedReader(new FileReader(sendfile));) {
				String line = null;
				while ((line = reader.readLine()) != null) {
					String[] context = line.split("#");
					String orderno = context[3].substring(7);
					String status = context[4].substring(7);
					if (status.equals("SEND")) {
						sendMap.putIfAbsent(orderno, status);
					} else if (status.equals("FAIL")) {
						failMap.putIfAbsent(orderno, status);
					}
				}
			} catch (IOException e) {
				System.err.println(sendfile.getName() + "读取错误：" + e.getMessage());
			}
		}

	}

}

package com.cmsz.check;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * @author yanjc
 * @date 2017年4月11日
 */
public class Utils {

	public static int top = 10;

	public static String decode(File srcFile) throws IOException {
		String result = null;
		BufferedInputStream bif = new BufferedInputStream(new FileInputStream(srcFile));
		byte[] buffer = new byte[bif.available()];
		bif.read(buffer);
		bif.close();
		result = new String(Base64.getDecoder().decode(buffer));
		return result;
	}

	public static void compareSet(HashSet<String> examSet, HashSet<String> standardSet) {
		// hashset 交集
		HashSet<String> interSet = new HashSet<String>();
		interSet.addAll(examSet);
		interSet.retainAll(standardSet);

		examSet.removeAll(interSet);
		standardSet.removeAll(interSet);

		if (!examSet.isEmpty()) {
			System.err.print("result文件多了");
			System.err.println(examSet.size() > top ? examSet.size() + "条记录" : ":" + examSet.toString());
			if (examSet.size() > top) {
				System.err.println("以下是部分记录");
				Stream<Object> s = Stream.of(examSet.toArray());
				s.map(Object::toString).limit(top).forEach(t -> System.err.println(t));
			}
		}

		if (!standardSet.isEmpty()) {
			System.err.print("标准文件少了");
			System.err.println(standardSet.size() > top ? standardSet.size() + "条记录" : ":" + standardSet.toString());
			if (standardSet.size() > top) {
				System.err.println("以下是部分差异");
				Stream<Object> s = Stream.of(standardSet.toArray());
				s.map(Object::toString).limit(top).forEach(t -> System.err.println(t));
			}
		}

	}
}

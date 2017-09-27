package com.cmsz;

/**
 * Analyse接口定义了日志分析的入口
 *
 * 请实现Analyse接口
 */
public interface Analyse {

	/**
	 * 传入日志文件所在目录，
	 * 请按要求分析日志，并在目录下生成result文件
	 * 
	 * @param path - 日志文件所在目录
	 */
	public void doAnalyse(String path);
}

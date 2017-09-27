package com.cmsz;

import java.io.File;

/**
 * 启动类
 *
 * 题目要求：
 * 1. 实现Analyse接口，传入一个文件目录，解析目录下的所有send与receive的日志文件，并生成result文件。
 * 2. 具体文件分析要求如下：
 * 		2.1 某个流水号（Serial）在send文件中状态为SEND且在receive文件中状态为RECEIVE，则结果为SUCCESS
 * 		2.2 不满足以上条件的流水号，则结果为FAIL
 * 		2.3 下面是示例，请仔细阅读
 * 
 * send 文件示例
 * INFO#2017-04-05 18:06:39.042#IP:127.0.0.1#Serial:2007347272432097600090#STATUS:SEND
 * INFO#2017-04-05 18:07:23.119#IP:127.0.0.1#Serial:1009147274026076200154#STATUS:SEND
 * INFO#2017-04-05 18:07:36.243#IP:127.0.0.1#Serial:1013147266715337000105#STATUS:SEND
 * INFO#2017-04-05 18:07:36.472#IP:127.0.0.1#Serial:2005947268182537000136#STATUS:FAIL
 * 
 * receive 文件示例
 * INFO#2017-04-05 18:06:39.158#IP:127.0.0.1#Serial:2007347272432097600090#STATUS:RECEIVE
 * INFO#2017-04-05 18:07:36.584#IP:127.0.0.1#Serial:1013147266715337000105#STATUS:FAIL
 * INFO#2017-04-05 18:07:36.698#IP:127.0.0.1#Serial:2005947268182537000136#STATUS:RECEIVE
 * 
 * result文件示例
 * SUCCESS:
 * 2007347272432097600090
 * FAIL:
 * 1009147274026076200154
 * 1013147266715337000105
 * 2005947268182537000136
 */
public class Main {

	public static void main(String[] args){
		String path = "usecases" + File.separator + "stage01";
		Analyse analyse = new AnalyzeWuhang();
		analyse.doAnalyse(path);
	}
}

package wuhang;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import wuhang.file.FileMatcher;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class FileGenerator {

//	public static int totalRecordNums = 1_0000_0000;
//	public static int totalRecordNums = 1000_0000;
//	public static int totalRecordNums = 100_0000;
	public static int totalRecordNums = 10;

	public static int recordPerFile = 100_0000;

//	public static String fileNamePrefix = "HundredMilion";
//	public static String fileNamePrefix = "TenMilion";
//	public static String fileNamePrefix = "milion";
	public static String fileNamePrefix = "testFile";

	public static void main(String[] args) throws IOException {
		String[] columns = Arrays.asList(OrderBean.class.getDeclaredFields()).stream().map(Field::getName).toArray(String[]::new);
//		for (String column : columns) {
//			System.out.println(column);
//		}
		long start = System.currentTimeMillis();
		ICsvBeanWriter beanWriter = null;

		for(int i=0; i<totalRecordNums; ++i){
			if (i % recordPerFile == 0) {
				closeFile(beanWriter);
				// change file
				beanWriter = openNewFile(i/recordPerFile);
				beanWriter.writeHeader(columns);
				System.out.println("已写入" + i + "条记录");
			}

			OrderBean bean = createOrderBean();

			beanWriter.write(bean, columns);
		}

		closeFile(beanWriter);
		long end = System.currentTimeMillis();
		System.out.println("总耗时" + (end - start) + "ms");
	}

	private static OrderBean createOrderBean() {
		OrderBean bean = new OrderBean();
		bean.setReqTransID(SerialNumber.getInterTransID());
		bean.setReqSys("0075");
		bean.setOrderNo("0075" + SerialNumber.generateRandomSerial(28));
		return bean;
	}

	private static void closeFile(ICsvBeanWriter beanWriter) throws IOException {
		if (beanWriter != null) {
			beanWriter.close();
		}
	}

	private static ICsvBeanWriter openNewFile(int index) throws IOException {
		return new CsvBeanWriter(new FileWriter(FileMatcher.defaultDir + "\\" + fileNamePrefix + index+".csv"),	CsvPreference.STANDARD_PREFERENCE);
	}


}

package wuhang;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 随机序列生成器，采用随机算法确保序列号在小周期内不重复。
 * <p/>
 * 使用示例如下：
 * <pre>
 *		SerialNumber.getTransID()
 * </pre>
 * 
 * @author wuhang
 * 
 */
public class SerialNumber {

	/**
	 * 以随机种子初始化random对象
	 */
	private static final Random RANDOM = new SecureRandom();
	
	/**
	 * 内部交易码长度
	 */
	private static int INTER_TRANS_ID_LENGTH = 32;
	/**
	 * 操作流水号长度
	 */
	private static int CRM_TRANSACTION_ID_LENGTH = 32;

	/**
	 * 日期格式化模板
	 */
	private static ThreadLocal<SimpleDateFormat> dateFormatFactory = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmssSSS");
		}
	};

	private static final String DEFAULT_TANSID_TYPE = "10";
	private static final String DEFAULT_TANSID_PROCODE = "999";

	private static final char[] mm = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * 获取transactionId 操作流水号，生成规则 消息类型（10） + 省编码（999） + 17位日期毫秒数 + 随机数补齐32位
	 * 
	 * @return String transactionId
	 */
	public static String getTransactionID() {
		StringBuilder sb = new StringBuilder();
		sb.append(DEFAULT_TANSID_TYPE).append(DEFAULT_TANSID_PROCODE).append(dateFormatFactory.get().format(new Date()))
				.append(generateRandomSerial(CRM_TRANSACTION_ID_LENGTH - sb.length()));
		return sb.toString();
	}

	/**
	 * 获取内部流水号，生成规则 消息类型（10） + 17位日期毫秒数 + 随机数补齐32位
	 * 
	 * @return String interTransId
	 */
	public static String getInterTransID() {
		StringBuilder sb = new StringBuilder();
		sb.append(DEFAULT_TANSID_TYPE).append(dateFormatFactory.get().format(new Date()))
				.append(generateRandomSerial(INTER_TRANS_ID_LENGTH - sb.length()));
		return sb.toString();
	}

	/**
	 * 生成len长度的随机数序列，适合自定义串的长度
	 * 
	 * @param len
	 *            -
	 * @return String randomString
	 */
	public static String generateRandomSerial(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(mm[RANDOM.nextInt(mm.length)]);
		}
		return sb.toString();
	}

}
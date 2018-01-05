package wuhang;

public class OrderBean {

	// 32位
	private String ReqTransID;

	// 4位
	private String reqSys;

	// 32位
	private String OrderNo;

	private String BuyerID = "wuhangdeyouxiang@126.com";

	private int OrderMoney = 100;

	private int Payment = 99;

	private int Gift = 1;

	private String MerActivityID = "BZ6630N";

	private String PaymentType = "ALIPAY-WEB";

	private String PaymentLimit = "CreditPay";

	private String ProductID = "EYE450505";

	private String ProductName = "产品名称";

	private String ProductDesc = "产品描述";

	private String ProductURL = "http://product";

	private String NotifyURL = "http://wuhang.buy";

	private String ReturnURL = "http://wuhang.buy";

	private String ClientIP = "127.0.0.1";

	private String CustomParam = "A=a|B=b";

	private String WeiXinAppId = "appid";

	private String WeiXinOpenId = "openid";

	private String IDType = "01";

	private String IDValue = "13925219347";

	private String DefaultBank;

	private String ProductType;

	private String ReqChannel;

	public String getReqTransID() {
		return ReqTransID;
	}

	public void setReqTransID(String reqTransID) {
		ReqTransID = reqTransID;
	}

	public String getReqSys() {
		return reqSys;
	}

	public void setReqSys(String reqSys) {
		this.reqSys = reqSys;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getBuyerID() {
		return BuyerID;
	}

	public void setBuyerID(String buyerID) {
		BuyerID = buyerID;
	}

	public int getOrderMoney() {
		return OrderMoney;
	}

	public void setOrderMoney(int orderMoney) {
		OrderMoney = orderMoney;
	}

	public int getPayment() {
		return Payment;
	}

	public void setPayment(int payment) {
		Payment = payment;
	}

	public int getGift() {
		return Gift;
	}

	public void setGift(int gift) {
		Gift = gift;
	}

	public String getMerActivityID() {
		return MerActivityID;
	}

	public void setMerActivityID(String merActivityID) {
		MerActivityID = merActivityID;
	}

	public String getPaymentType() {
		return PaymentType;
	}

	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}

	public String getPaymentLimit() {
		return PaymentLimit;
	}

	public void setPaymentLimit(String paymentLimit) {
		PaymentLimit = paymentLimit;
	}

	public String getProductID() {
		return ProductID;
	}

	public void setProductID(String productID) {
		ProductID = productID;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductDesc() {
		return ProductDesc;
	}

	public void setProductDesc(String productDesc) {
		ProductDesc = productDesc;
	}

	public String getProductURL() {
		return ProductURL;
	}

	public void setProductURL(String productURL) {
		ProductURL = productURL;
	}

	public String getNotifyURL() {
		return NotifyURL;
	}

	public void setNotifyURL(String notifyURL) {
		NotifyURL = notifyURL;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public String getClientIP() {
		return ClientIP;
	}

	public void setClientIP(String clientIP) {
		ClientIP = clientIP;
	}

	public String getCustomParam() {
		return CustomParam;
	}

	public void setCustomParam(String customParam) {
		CustomParam = customParam;
	}

	public String getWeiXinAppId() {
		return WeiXinAppId;
	}

	public void setWeiXinAppId(String weiXinAppId) {
		WeiXinAppId = weiXinAppId;
	}

	public String getWeiXinOpenId() {
		return WeiXinOpenId;
	}

	public void setWeiXinOpenId(String weiXinOpenId) {
		WeiXinOpenId = weiXinOpenId;
	}

	public String getIDType() {
		return IDType;
	}

	public void setIDType(String IDType) {
		this.IDType = IDType;
	}

	public String getIDValue() {
		return IDValue;
	}

	public void setIDValue(String IDValue) {
		this.IDValue = IDValue;
	}

	public String getDefaultBank() {
		return DefaultBank;
	}

	public void setDefaultBank(String defaultBank) {
		DefaultBank = defaultBank;
	}

	public String getProductType() {
		return ProductType;
	}

	public void setProductType(String productType) {
		ProductType = productType;
	}

	public String getReqChannel() {
		return ReqChannel;
	}

	public void setReqChannel(String reqChannel) {
		ReqChannel = reqChannel;
	}
}

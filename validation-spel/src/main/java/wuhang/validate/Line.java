package wuhang.validate;

import java.util.Map;

public class Line {

	private String[] headers;

	private Map<String, String> line;

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public Map<String, String> getLine() {
		return line;
	}

	public void setLine(Map<String, String> line) {
		this.line = line;
	}
}

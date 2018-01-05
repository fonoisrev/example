package wuhang.file;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

@Component
public class FileMatcher {

	public static String defaultDir = "D:\\testFiles";

	public File[] matchFiles(String fileNameExp) {
		File base = new File(defaultDir);

		File[] files = base.listFiles(pathname -> {
			 return Pattern.matches(fileNameExp, pathname.getName());
		});
		return files;
	}
}

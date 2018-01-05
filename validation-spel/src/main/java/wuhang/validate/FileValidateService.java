package wuhang.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import wuhang.rule.RulesLoader;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class FileValidateService {

	@Autowired
	CsvFileValidator csvFileValidator;

	@Autowired
	RulesLoader rulesLoader;

	public String validate(String ruleName, File... files) {
		String[] rules = rulesLoader.loadRules(ruleName);
		StringBuilder sb = new StringBuilder();

		if (ObjectUtils.isEmpty(rules)) {
			return "no rules";
		}

		Future<String>[] tasks = new Future[files.length];
		for (int i=0; i<files.length; ++i){
			tasks[i] = csvFileValidator.execute(files[i], rules);
		}
		for(Future<String> task : tasks ){
			String taskResult = null;
			try {
				taskResult = task.get();
			} catch (InterruptedException|ExecutionException e) {
			}
			if (StringUtils.hasText(taskResult)) {
				sb.append("<p>").append(taskResult).append("</p>\r\n");
			}

		}

		return !StringUtils.hasText(sb) ? "Success" : sb.toString();
	}
}

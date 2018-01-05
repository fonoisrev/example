package wuhang.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.supercsv.io.CsvMapReader;
import org.supercsv.prefs.CsvPreference;
import wuhang.rule.RulesLoader;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class CsvFileValidator {

	@Async("myThreadPool")
	public Future<String> execute(File file, String[] rules) {
		System.out.println("开始处文件" + file.getPath());
		long start = System.currentTimeMillis();

		StringBuilder result = new StringBuilder();
		SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
				this.getClass().getClassLoader());
		ExpressionParser parser = new SpelExpressionParser(config);
		StandardEvaluationContext context = new StandardEvaluationContext();

		Expression[] ruleExps = Arrays.stream(rules).map(rule -> {
			return parser.parseExpression(rule);
		}).toArray(Expression[]::new);

		try (CsvMapReader reader = new CsvMapReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);
		) {
			Line line = new Line();
			context.setRootObject(line);
			String[] headers = reader.getHeader(true);
			line.setHeaders(headers);
			for (; ; ) {
				Map<String, String> lineMap = reader.read(headers);
				if (ObjectUtils.isEmpty(lineMap)) {
					break;
				}
				line.setLine(lineMap);

				for (Expression exp : ruleExps) {

					boolean valid = true;
					try {
						valid = exp.getValue(context, boolean.class);
					} catch (EvaluationException e) {
						valid = false;
					}
					if (!valid) {
						if (!StringUtils.hasText(result)) {
							result.append("file:" + file.getPath() + "<br/>\r\n");
						}
						int lineNum = reader.getLineNumber();
						result.append(String.format("line:%d validate error: rule %s return false <br/>\r\n", lineNum, exp.getExpressionString()));
					}
				}
			}

		} catch (Exception e) {
			result.append("file read exception in " + file.getPath() + "<p>\r\n");
			result.append(e.getClass().getName() + ":" + e.getMessage());
		}

		long end = System.currentTimeMillis();
		System.out.println(file.getPath() + "处理结束，耗时" + (end - start) + "ms");

		return new AsyncResult<String>(result.toString());
	}

}

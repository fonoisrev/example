package wuhang.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Set;

@Component
public class RulesLoader {

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	SetOperations<String, String> setOperations;

	@PostConstruct
	public void init(){
		setOperations = redisTemplate.opsForSet();
	}

	public String[] loadRules(String ruleExp){
		Set<String> resultSet = setOperations.members(ruleExp);
		return ObjectUtils.isEmpty(resultSet)? null : resultSet.toArray(new String[]{});
	}
}

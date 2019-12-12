package com.design.pattern;

import com.design.pattern.mapper.RunoobTbMapper;
import com.design.pattern.vo.RunoobTb;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class PatternApplicationTests {
	@Resource
	private RunoobTbMapper runoobTbMapper;
	@Test
	void contextLoads() {

	}
	@Test
	void dataTest(){
		RunoobTb runoobTb=runoobTbMapper.getById(1);
		log.info(runoobTb.toString());
	}

}

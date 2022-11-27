package org.zerock.springex.sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)  // JUnit5 버전에서 'Spring-test를 이용하기 위한 설정
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
/**
     스프링의 설정 정보를 로딩하기 위해서 사용 함
     현재 프로젝트의 경우  XMl로 설정되어 있기 때문에 @ContextConfiguration의 locations 속성을 이용하고, 자바 설정을 이용하는 경우에는 classes 속성을 이용합니다.
*/
public class SampleTests {

    @Autowired// 의존성 주입
    private SampleService sampleService;

    @Test
    public void testService1() {
        log.info(sampleService);
        Assertions.assertNotNull(sampleService);
    }
}

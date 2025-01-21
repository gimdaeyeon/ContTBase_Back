package com.app.contbase.service.ppt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class PPTServiceTest {

    @Autowired
    PPTServiceImpl pptService;

    @Test
    @DisplayName("ppt생성 테스트")
    void createSubtitlePPT(){
        // given
        String subtitles = """
                해당 테스트를 통과하게 되면
                먼저는 PPT가 생성이 되어야 하고
                
                ppt의 슬라이드는
                2개여야 합니다.
                """;
        // when

        // then
//        경로에 ppt파일이 존재하는지 확인
    }

}
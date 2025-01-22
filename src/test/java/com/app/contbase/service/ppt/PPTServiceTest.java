package com.app.contbase.service.ppt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

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
                안녕하세요
                
                저의 이름은
                
                ppt입니다
                
                """;
        // when

        String filePath = pptService.createPPT("test", subtitles);
        File pptFile = new File(filePath);

        // then
        assertThat(pptFile.exists()).isTrue();
    }


}
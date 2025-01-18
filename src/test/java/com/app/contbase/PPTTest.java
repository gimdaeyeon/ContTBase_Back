package com.app.contbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.xslf.usermodel.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PPTTest {

    @Test
    void pptTextTest() throws FileNotFoundException {
//        새 프레젠테이션 생성
        XMLSlideShow ppt = new XMLSlideShow();

//        첫 번째 슬라이드 추가
        XSLFSlide slide = ppt.createSlide();

//        텍스트 박스 생성 및 텍스트 설정
        XSLFTextBox textBox = slide.createTextBox();
        textBox.setAnchor(new Rectangle2D.Double(100, 50, 500, 150));
//        텍스트박스에서 위아래 정렬
//        shape.setVerticalAlignment(VerticalAlignment.MIDDLE);
//        텍스트박스에서 중앙정렬
        textBox.setHorizontalCentered(true);

        XSLFTextParagraph p = textBox.addNewTextParagraph();
//        텍스트 중앙 정렬
        p.setTextAlign(TextParagraph.TextAlign.CENTER);

        XSLFTextRun r1 = p.addNewTextRun();
//        r1.setFontColor(Color.red);
        r1.setFontSize(50.0);
        r1.setBold(true);
//        r1.setFontFamily("MV Boli");
        r1.setText("Hello PPT\nMy name is Java");


//        프세젠테이션 저장
        try (FileOutputStream out = new FileOutputStream("textTest.pptx")) {
            ppt.write(out);
            log.info("프레젠테이션이 생성되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 주의사항
        1. Apache poi 라이브러리에는 직접적으로 배경을 설정하는 API는 없다. 따라서
           슬라이드를 생성하고 해당 슬라이드의 크기만큼 이미지를 조정한 후 삽입을 하여
           배경인 것 처럼 보이게 끔 해야한다.
        2. 슬라이드 안에서 생성된 요소들 간의 순서를 설정하는 API가 없기 때문에 나중에 생성된 요소가
           먼저 생성된 요소를 덮어버릴 수 있다. 따라서 ppt를 생성할 때 각 요소간의 생성 순서를 잘 고려해서
           생성해야한다.
     */
    @Test
    void pptBackgroundTest() throws IOException {
//        새 프레젠테이션 생성
        XMLSlideShow ppt = new XMLSlideShow();

//        슬라이드 크기 가져오기
        Dimension pageSize = ppt.getPageSize();
        double slideWidth = pageSize.getWidth();
        double slideHeight = pageSize.getHeight();

//        이미지 파일 읽기
        String imagePath = "D:/personal_project/contTbase/resource/sampleImg/test3.jpg";
        FileInputStream imageInputStream = new FileInputStream(imagePath);

//        이미지 데이터를 추가
        XSLFPictureData pictureData = ppt.addPicture(imageInputStream, PictureData.PictureType.JPEG);

//        첫 번째 슬라이드 추가
        XSLFSlide slide = ppt.createSlide();

//        슬라이드 전체를 덮는 이미지 쉐이프 추가
        XSLFPictureShape picture = slide.createPicture(pictureData);
        picture.setAnchor(new Rectangle2D.Double(0, 0, slideWidth, slideHeight));

//        배경색 추가
//        XSLFBackground background = slide.getBackground();
//        background.setFillColor(Color.blue);

//        프세젠테이션 저장
        try (FileOutputStream out = new FileOutputStream("backgroundTest.pptx")) {
            ppt.write(out);
            log.info("프레젠테이션이 생성되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    @DisplayName("이미지 배경과 자막을 가지는 ppt를 생성하는 테스트")
    void basicSubTitle() throws IOException {
        try (XMLSlideShow ppt = new XMLSlideShow()) {

            Dimension pageSize = ppt.getPageSize();
            double slideWidth = pageSize.getWidth();
            double slideHeight = pageSize.getHeight();

            String imagePath = "D:/personal_project/contTbase/resource/sampleImg/test4.jpg";
            FileInputStream imageInputStream = new FileInputStream(imagePath);

            XSLFPictureData pictureData = ppt.addPicture(imageInputStream, PictureData.PictureType.JPEG);

            XSLFSlide slide = ppt.createSlide();

            XSLFPictureShape picture = slide.createPicture(pictureData);

            XSLFTextBox textBox = slide.createTextBox();

            double textBoxWidth = 600;
            double textBoxHeight = 150;

            textBox.setAnchor(new Rectangle2D.Double((slideWidth - textBoxWidth) / 2, 50, textBoxWidth, textBoxHeight));
            textBox.setHorizontalCentered(true);

            XSLFTextParagraph p = textBox.addNewTextParagraph();
            p.setTextAlign(TextParagraph.TextAlign.CENTER);

            XSLFTextRun r1 = p.addNewTextRun();
            r1.setFontSize(50.0);
            r1.setFontColor(Color.WHITE);
//        r1.setFontFamily("Baloo");
            r1.setBold(true);
            r1.setText("헬로 월드\nMy name is Java");

            picture.setAnchor(new Rectangle2D.Double(0, 0, slideWidth, slideHeight));

            try (FileOutputStream out = new FileOutputStream("basicTest.pptx")) {
                ppt.write(out);
                log.info("프레젠테이션이 생성되었습니다.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    @DisplayName("긴 문자열을 엔터를 기준으로 배열로 분리하기")
    void splitSubtitlesTest(){
        String subtitles = """
                이것은 ppt를 만들기 위한
                프로젝트 입니다
                
                문자열들 사이에
                한 칸을 띄우게 되면
                
                새로운 슬라이드를 생성해서
                ppt를 생성하게 됩니다.
                
                따라서 이 테스트를 기반으로
                생성된 ppt의 총 페이지 갯수는
                
                5개가 되어야 합니다.
                """;

//        클라이언트의 os에 따라 개행문자가 달라질 수 있기 때문에 System.lineSeparator()가 아닌 정규표현식을 사용해서 문자열을 분리
//        String[] split = subtitles.split(System.lineSeparator());
//
        String[] split = subtitles.split("\\R{2,}");
        log.info("length: {}",split.length);
        log.info("split = {}", Arrays.toString(split));

        assertThat(split.length).isEqualTo(5);
    }

}










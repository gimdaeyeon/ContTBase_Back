package com.app.contbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.xslf.usermodel.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

    @Test
    void pptBackgroundTest() throws IOException {
//        새 프레젠테이션 생성
        XMLSlideShow ppt = new XMLSlideShow();


//        첫 번째 슬라이드 추가
        XSLFSlide slide = ppt.createSlide();

        XSLFBackground background = slide.getBackground();
        background.setFillColor(Color.blue);

//        프세젠테이션 저장
        try (FileOutputStream out = new FileOutputStream("backgroundTest.pptx")) {
            ppt.write(out);
            log.info("프레젠테이션이 생성되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

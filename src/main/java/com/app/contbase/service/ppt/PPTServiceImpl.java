package com.app.contbase.service.ppt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.xslf.usermodel.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PPTServiceImpl implements PPTService {

    private static final String DOUBLE_LINE_BREAK_PATTERN = "\\R{2,}";
    private static final String PPT_EXTENSION = ".pptx";


    @Override
    public String createPPT(String fileName, String subtitle) {
        String filePath = fileName + PPT_EXTENSION;


        String[] subtitles = subtitle.split(DOUBLE_LINE_BREAK_PATTERN);

        try (XMLSlideShow ppt = new XMLSlideShow()) {
            Dimension pageSize = ppt.getPageSize();
            double slideWidth = pageSize.getWidth();

            double textBoxWidth = 600;
            double textBoxHeight = 150;

            for (String slideText : subtitles) {
                XSLFSlide slide = ppt.createSlide();

                XSLFTextBox textBox = slide.createTextBox();

                textBox.setAnchor(new Rectangle2D.Double((slideWidth - textBoxWidth) / 2, 50, textBoxWidth, textBoxHeight));
                textBox.setHorizontalCentered(true);

                XSLFTextParagraph p = textBox.addNewTextParagraph();
                p.setTextAlign(TextParagraph.TextAlign.CENTER);

                XSLFTextRun textRun = p.addNewTextRun();
                textRun.setFontSize(45.0);
                textRun.setBold(true);

                textRun.setText(slideText);

                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    ppt.write(out);
                    log.info("프레젠테이션 생성");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            log.error("createPPT:", e);
            return null;
        }

        return filePath;
    }
}

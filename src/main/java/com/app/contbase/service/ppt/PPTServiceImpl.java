package com.app.contbase.service.ppt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.springframework.stereotype.Service;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PPTServiceImpl implements PPTService {

    private static final String DOUBLE_LINE_BREAK_PATTERN = "\\R{2,}";
    @Override
    public String createPPT(String subtitle) {

        String[] subtitles = subtitle.split(DOUBLE_LINE_BREAK_PATTERN);

        try (XMLSlideShow ppt = new XMLSlideShow()) {

            for (String slideText : subtitles) {
                XSLFSlide slide = ppt.createSlide();

                XSLFTextBox textBox = slide.createTextBox();
                textBox.setAnchor(new Rectangle2D.Double(100, 50, 500, 150));

            }

        }catch (IOException e){
            log.error("createPPT:",e);
        }

        return null;
    }
}

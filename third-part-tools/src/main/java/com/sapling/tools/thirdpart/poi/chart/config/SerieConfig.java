package com.sapling.tools.thirdpart.poi.chart.config;

import org.apache.poi.xddf.usermodel.PresetColor;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/21
 * @since v1.0
 */
public class SerieConfig {

    private PresetColor color;

    private int sheetIndex;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public PresetColor getColor() {
        return color;
    }

    public void setColor(PresetColor color) {
        this.color = color;
    }
}

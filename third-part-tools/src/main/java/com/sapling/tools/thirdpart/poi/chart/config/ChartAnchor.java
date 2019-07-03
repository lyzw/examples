package com.sapling.tools.thirdpart.poi.chart.config;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/21
 * @since v1.0
 */
public class ChartAnchor {

    int startColumn = 0;

    int startRow = 0;

    int height = 10;

    int width = 10;

    public ChartAnchor(int startColumn, int startRow) {
        this.startColumn = startColumn;
        this.startRow = startRow;
    }

    public ChartAnchor(int startColumn, int startRow, int height, int width) {
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.height = height;
        this.width = width;
    }

    public int getEndRow(){
        return startRow + height;
    }

    public int getEndColumn(){
        return startColumn + width;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

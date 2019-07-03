package com.sapling.tools.thirdpart.poi.chart.config;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/21
 * @since v1.0
 */
public class ColumnSerieConfig  extends SerieConfig{

    private int column;
    private int beginRow;
    private int endRow;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getBeginRow() {
        return beginRow;
    }

    public void setBeginRow(int beginRow) {
        this.beginRow = beginRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }
}

package com.sapling.tools.thirdpart.poi.chart.config;


import org.apache.poi.xddf.usermodel.PresetColor;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/21
 * @since v1.0
 */
public class RowSerieConfig extends SerieConfig {

    public RowSerieConfig(){
        super();
    }
    public RowSerieConfig(String title, int row, int beginColumn, int endColumn) {
        super();
        this.setTitle(title);
        this.row = row;
        this.beginColumn = beginColumn;
        this.endColumn = endColumn;
    }

    public RowSerieConfig(String title, int row, int beginColumn, int endColumn, PresetColor color) {
        super();
        this.setTitle(title);
        this.setColor(color);
        this.row = row;
        this.beginColumn = beginColumn;
        this.endColumn = endColumn;
    }

    private int row;
    private int beginColumn;
    private int endColumn;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getBeginColumn() {
        return beginColumn;
    }

    public void setBeginColumn(int beginColumn) {
        this.beginColumn = beginColumn;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }
}

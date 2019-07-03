package com.sapling.tools.thirdpart.poi.chart;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.sapling.tools.thirdpart.poi.chart.config.ColumnSerieConfig;
import com.sapling.tools.thirdpart.poi.chart.config.RowSerieConfig;
import com.sapling.tools.thirdpart.poi.chart.config.SerieConfig;


/**
 * Excel图表辅助类
 *
 * @author weizhou
 * @version v1.0
 * @date 2018/11/23
 * @since v1.0
 */
public class ChartHelper {

    /**
     * 根据给定的列序号获取对应的列名称，如0->A，25->Z，26->AA
     *
     * @param value 列序号
     * @return 返回列序号
     */
    public static String getColumnName(int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        if (value < 26) {
            return new String(new char[]{(char) (65 + value)});
        }
        return new String(
                getColumnName((value / 26) - 1) + getColumnName(value % 26));
    }

    /**
     * 根据配置获取图表的数据的位置引用
     *
     * @param sheet       数据所在的sheet
     * @param serieConfig 配置信息
     * @return 引用信息
     */
    public static String getPositionRef(Sheet sheet, SerieConfig serieConfig) {
        if (serieConfig == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(sheet.getSheetName()).append("!$");
        if (serieConfig instanceof RowSerieConfig) {
            RowSerieConfig config = (RowSerieConfig) serieConfig;
            sb.append(getColumnName(config.getBeginColumn()))
                    .append("$")
                    .append(config.getRow() + 1)
                    .append(":$")
                    .append(getColumnName(config.getEndColumn()))
                    .append("$")
                    .append(config.getRow() + 1);
        } else if (serieConfig instanceof ColumnSerieConfig) {
            ColumnSerieConfig config = (ColumnSerieConfig) serieConfig;
            String columnName = getColumnName(config.getColumn());
            sb.append(columnName)
                    .append("$")
                    .append(config.getBeginRow() + 1)
                    .append(":$")
                    .append(columnName)
                    .append("$")
                    .append(config.getEndRow() + 1);
        }
        return sb.toString();
    }

    /**
     * 根据配置获取Category数据源
     *
     * @param xssfSheet    数据所在的sheet
     * @param seriesConfig 配置信息
     * @return
     */
    public static XDDFNumericalDataSource<Double> getSeriesCategory(XSSFSheet xssfSheet, SerieConfig seriesConfig) {
        return getValueDataSource(xssfSheet, seriesConfig);
    }

    /**
     * 根据配置获取Value的数据源
     *
     * @param xssfSheet    数据所在的sheet
     * @param seriesConfig 配置信息
     * @return
     */
    public static XDDFNumericalDataSource<Double> getValueDataSource(XSSFSheet xssfSheet, SerieConfig seriesConfig) {
        if (seriesConfig instanceof RowSerieConfig) {
            RowSerieConfig config = (RowSerieConfig) seriesConfig;
            return XDDFDataSourcesFactory.fromNumericCellRange(xssfSheet,
                    new CellRangeAddress(config.getRow(), config.getRow(), config.getBeginColumn(), config.getEndColumn()));
        } else if (seriesConfig instanceof ColumnSerieConfig) {
            ColumnSerieConfig config = (ColumnSerieConfig) seriesConfig;
            return XDDFDataSourcesFactory.fromNumericCellRange(xssfSheet,
                    new CellRangeAddress(config.getBeginRow(), config.getEndRow(), config.getColumn(), config.getColumn()));
        } else {
            return null;
        }
    }
}

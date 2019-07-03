package com.sapling.tools.thirdpart.poi.chart;

import java.util.LinkedList;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.sapling.tools.thirdpart.poi.chart.config.ChartAnchor;
import com.sapling.tools.thirdpart.poi.chart.config.SerieConfig;


/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/23
 * @since v1.0
 */
public interface ChartBuilder {

    /**
     * build the chart
     *
     * @param xssfSheet      sheet
     * @param title          chart title
     * @param chartAnchor    chart Anchor {@link ChartAnchor}
     * @param categoryConfig category config
     * @param valueConfig    value config
     */
    void buildChart(XSSFSheet xssfSheet, String title, ChartAnchor chartAnchor, SerieConfig categoryConfig, LinkedList<SerieConfig> valueConfig);


}

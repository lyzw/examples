package com.sapling.tools.thirdpart.poi.chart.xddf;

import java.util.LinkedList;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.sapling.tools.thirdpart.poi.chart.ChartBuilder;
import com.sapling.tools.thirdpart.poi.chart.config.ChartAnchor;
import com.sapling.tools.thirdpart.poi.chart.config.SerieConfig;


/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/21
 * @since v1.0
 */
public abstract class AbstractXddfChartBuilder implements ChartBuilder {

    /**
     * the type of chart
     */
    private ChartTypes chartType;

    /**
     * the position of legend
     */
    private LegendPosition legendPosition = LegendPosition.TOP_RIGHT;


    public AbstractXddfChartBuilder(ChartTypes chartType) {
        this.chartType = chartType;
    }

    @Override
    /**
     * 创建图表，首先创建不同的chart实例，再往chart中填充不同的数据
     * 根据chart中数据的多少可以分为单个和多个，其中bar、scatter、line支持多列（行）数据，pie支持单列（行）数据
     *
     * @param xssfSheet      sheet
     * @param title          chart title
     * @param chartAnchor    chart Anchor {@link ChartAnchor}
     * @param categoryConfig category config
     * @param valueConfig    value config
     */
    public void buildChart(XSSFSheet xssfSheet, String title, ChartAnchor chartAnchor, SerieConfig categoryConfig, LinkedList<SerieConfig> valueConfig) {
        XSSFChart chart = getAndConfigChart(xssfSheet, title, chartAnchor);
        getAndConfigChartData(xssfSheet, chart, categoryConfig, valueConfig);
    }

    /**
     * 获取并配置Chart的数据
     *
     * @param xssfSheet
     * @param chart
     * @param categoryConfig
     * @param valueConfig
     */
    abstract void getAndConfigChartData(XSSFSheet xssfSheet, XSSFChart chart, SerieConfig categoryConfig, LinkedList<SerieConfig> valueConfig);


    /**
     * get and config the chart entity
     *
     * @param sheet       the sheet which the chart belong to
     * @param title       the title of the chart
     * @param chartAnchor the anchor of chart
     * @return return chart entity {@link XSSFChart}
     */
    public XSSFChart getAndConfigChart(XSSFSheet sheet, String title, ChartAnchor chartAnchor) {
        //获取画板
        XSSFDrawing xssfDrawing = sheet.createDrawingPatriarch();
        //设置画板定位
        ClientAnchor anchor = xssfDrawing.createAnchor(0, 0, 0, 0,
                chartAnchor.getStartColumn(), chartAnchor.getStartRow(),
                chartAnchor.getEndColumn(), chartAnchor.getEndRow());
        //根据创建图表
        XSSFChart chart = xssfDrawing.createChart(anchor);
        //设置图表标题
        chart.setTitleText(title);
        //获取注释信息
        XDDFChartLegend xddfChartLegend = chart.getOrAddLegend();
        xddfChartLegend.setPosition(legendPosition);
        xddfChartLegend.setOverlay(false);
        return chart;
    }


    public LegendPosition getLegendPosition() {
        return legendPosition;
    }

    public void setLegendPosition(LegendPosition legendPosition) {
        this.legendPosition = legendPosition;
    }

    public ChartTypes getChartType() {
        return chartType;
    }


}


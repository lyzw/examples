package com.sapling.tools.thirdpart.poi.chart.xddf;

import java.util.LinkedList;

import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.sapling.tools.thirdpart.poi.chart.ChartHelper;
import com.sapling.tools.thirdpart.poi.chart.config.SerieConfig;


/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/23
 * @since v1.0
 */
public abstract class AbstractMultiXddfChartBuilder extends AbstractXddfChartBuilder {


    /**
     * the cross type of axis
     */
    private AxisCrosses axisCrosses;


    public AbstractMultiXddfChartBuilder(ChartTypes chartType) {
        super(chartType);
    }

    /**
     * @param chart
     * @return
     */
    public XDDFChartData getChartData(XSSFChart chart, ChartTypes chartTypes) {
        if (chartTypes == null) {
            throw new IllegalArgumentException("chart type is Illegal! pls check it!");
        }

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        if (axisCrosses == null) {
            axisCrosses = AxisCrosses.AUTO_ZERO;
        }
        leftAxis.setCrosses(axisCrosses);
        //柱形图，可以将位置设置为两个坐标的中间-AxisCrossBetween.BETWEEN，防止两端的数据显示不全
        if (chartTypes.equals(ChartTypes.BAR) || chartTypes.equals(ChartTypes.RADAR)) {
            leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);
        }

        return chart.createData(chartTypes, bottomAxis, leftAxis);
    }

    /**
     * 生成数据
     *
     * @param xssfSheet   sheet
     * @param serieConfig 配置
     * @param configs     using {@link LinkedList < SerieConfig > } to control the sequence of series
     */
    @Override
    public void getAndConfigChartData(XSSFSheet xssfSheet, XSSFChart chart,
                                      SerieConfig serieConfig,
                                      LinkedList<SerieConfig> configs) {
        XDDFChartData data = getChartData(chart, getChartType());
        XDDFDataSource<Double> categoryDataSource = ChartHelper.getSeriesCategory(xssfSheet, serieConfig);
        ((XDDFNumericalDataSource<Double>) categoryDataSource).setFormatCode("General");
        for (int index = 0; index < configs.size(); index++) {
            SerieConfig item = configs.get(index);
            XDDFNumericalDataSource<Double> valueDataSource = ChartHelper.getValueDataSource(xssfSheet, item);
            valueDataSource.setFormatCode("General");
            data.addSeries(categoryDataSource, valueDataSource);
            System.out.println("========" + item.getTitle());
        }
        chart.plot(data);
        //设置图表特征
        configChartFeature(chart, data, 0);

        if (!getChartType().equals(ChartTypes.RADAR)) {
            //设置标题与颜色
            for (int index = 0; index < configs.size(); index++) {
                SerieConfig item = configs.get(index);
                configSeriesTitle(chart, data, item.getTitle(), 0, index);
                //设置颜色
                if (item.getColor() == null) {
                    fillSeriesSolidProperties(data, index, PresetColor.values()[
                            10 + index]);
                } else {
                    fillSeriesSolidProperties(data, index, item.getColor());
                }

            }
        }

        System.out.println(chart.getCTChart());
    }


    /**
     * 设置Series的名称
     *
     * @param chart      图表
     * @param data       图表数据
     * @param title      标题名称
     * @param chartIndex 图表位置
     * @param index      线条位置
     */
    public abstract void configSeriesTitle(XSSFChart chart, XDDFChartData data, String title,
                                           int chartIndex, int index);

    /**
     * 设置图表的类型特征
     *
     * @param chart
     * @param data
     * @param chartIndex
     */
    public abstract void configChartFeature(XSSFChart chart, XDDFChartData data,
                                            int chartIndex);


    public void fillSeriesSolidProperties(XDDFChartData data, int index, PresetColor presetColor) {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(presetColor));
        XDDFChartData.Series series = data.getSeries().get(index);
        XDDFShapeProperties properties = series.getShapeProperties();
        if (properties == null) {
            properties = new XDDFShapeProperties();
        }
        //        XDDFLineProperties lineProperties = new XDDFLineProperties();
        //        lineProperties.setPenAlignment(PenAlignment.IN);
        //        properties.setLineProperties(lineProperties);
        properties.setFillProperties(fill);
        series.setShapeProperties(properties);
    }

    public AxisCrosses getAxisCrosses() {
        return axisCrosses;
    }

    public void setAxisCrosses(AxisCrosses axisCrosses) {
        this.axisCrosses = axisCrosses;
    }

}

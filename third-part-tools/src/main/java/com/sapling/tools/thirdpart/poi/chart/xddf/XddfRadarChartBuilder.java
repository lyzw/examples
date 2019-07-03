package com.sapling.tools.thirdpart.poi.chart.xddf;

import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;


/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/23
 * @since v1.0
 */
public class XddfRadarChartBuilder extends AbstractMultiXddfChartBuilder {

    public XddfRadarChartBuilder() {
        super(ChartTypes.RADAR);
    }

    @Override
    public void configSeriesTitle(XSSFChart chart, XDDFChartData data, String title, int chartIndex, int index) {
        if (chart.getCTChart().getPlotArea().getRadarChartArray(chartIndex).getSerArray(index).getTx() ==
                null) {
            chart.getCTChart().getPlotArea().getRadarChartArray(chartIndex).getSerArray(index).addNewTx();
        }
        data.getSeries().get(index).setTitle(title, null);
    }

    @Override
    public void configChartFeature(XSSFChart chart, XDDFChartData data, int chartIndex) {
        chart.setAutoTitleDeleted(true);
        CTRadarChart ctRadarChart = chart.getCTChart().getPlotArea().getRadarChartArray(chartIndex);
        ctRadarChart.addNewAxId().setVal(chart.getAxes().get(0).getId());
        ctRadarChart.addNewAxId().setVal(chart.getAxes().get(1).getId());
        ctRadarChart.addNewVaryColors().setVal(false);
        ctRadarChart.addNewRadarStyle().setVal(org.openxmlformats.schemas.drawingml.x2006.chart.STRadarStyle.MARKER);
        CTDLbls ctdLbls = ctRadarChart.addNewDLbls();

        ctdLbls.addNewShowLegendKey().setVal(false);
        ctdLbls.addNewShowVal().setVal(false);
        ctdLbls.addNewShowCatName().setVal(false);
        ctdLbls.addNewShowPercent().setVal(false);
        ctdLbls.addNewShowBubbleSize().setVal(false);
        ctdLbls.addNewShowSerName().setVal(false);

    }
}

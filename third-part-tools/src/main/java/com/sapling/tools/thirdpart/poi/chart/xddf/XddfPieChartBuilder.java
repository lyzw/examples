package com.sapling.tools.thirdpart.poi.chart.xddf;

import java.util.LinkedList;

import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;

import com.sapling.tools.thirdpart.poi.chart.ChartHelper;
import com.sapling.tools.thirdpart.poi.chart.config.SerieConfig;


/**
 * <b>Usage:</b>
 * <p>
 *     // ----------  Make sample data -----------
 *      for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
 *             cell = row.createCell((short) colIndex);
 *             cell.setCellValue("测试" + colIndex);
 *         }
 *
 *         for (int rowIndex = 1; rowIndex < NUM_OF_ROWS; rowIndex++) {
 *             row = sheet.createRow((short) rowIndex);
 *             for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
 *                 cell = row.createCell((short) colIndex);
 *                 cell.setCellValue(new Random().nextInt(100));
 *             }
 *         }
 *         LinkedList<SerieConfig> list = new LinkedList<>();
 *         list.add(new RowSerieConfig("数据1", 1, 0, 9));
 *         list.add(new RowSerieConfig("数据2", 2, 0, 9));
 *         list.add(new RowSerieConfig("数据3", 3, 0, 9));
 *         list.add(new RowSerieConfig("数据4", 4, 0, 9));
 *         list.add(new RowSerieConfig("数据5", 5, 0, 9));
 *         list.add(new RowSerieConfig("数据6", 6, 0, 9));
 *
 *         XddfPieChartBuilder builder = new XddfPieChartBuilder();
 *         // set the legend position
 *         builder.setLegendPosition(LegendPosition.BOTTOM);
 *         RowSerieConfig serieConfig = new RowSerieConfig("横坐标", 0, 0, 9);
 *         builder.buildChart(sheet, "ssss", new ChartAnchor(0, 5, 20, 9), serieConfig, list);
 * </p>
 *
 * @author weizhou
 * @version v1.0
 * @date 2018/11/22
 * @since v1.0
 */
public class XddfPieChartBuilder extends AbstractSingleXddfChartBuilder {


    public XddfPieChartBuilder() {
        super(ChartTypes.PIE);
    }

    @Override
    void getAndConfigChartData(XSSFSheet xssfSheet, XSSFChart chart, SerieConfig categoryConfig, LinkedList<SerieConfig> valueConfig) {
        CTChart ctChart = chart.getCTChart();
        CTPlotArea ctPlotArea = ctChart.getPlotArea();
        CTPieChart ctPieChart = ctPlotArea.addNewPieChart();
        CTBoolean ctBoolean = ctPieChart.addNewVaryColors();
        ctBoolean.setVal(true);
        CTPieSer ctPieSer = ctPieChart.addNewSer();

        ctPieSer.addNewIdx().setVal(0);
        String cateRef = ChartHelper.getPositionRef(xssfSheet, categoryConfig);
        String valRef = ChartHelper.getPositionRef(xssfSheet, valueConfig.get(0));
        CTAxDataSource cttAxDataSource = ctPieSer.addNewCat();
        CTStrRef ctStrRef = cttAxDataSource.addNewStrRef();
        ctStrRef.setF(cateRef);
        CTNumDataSource ctNumDataSource = ctPieSer.addNewVal();
        CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
        ctNumRef.setF(valRef);
    }

}

package com.sapling.tools.thirdpart.poi.chart.xddf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sapling.tools.thirdpart.poi.chart.config.ChartAnchor;
import com.sapling.tools.thirdpart.poi.chart.config.RowSerieConfig;
import com.sapling.tools.thirdpart.poi.chart.config.SerieConfig;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/22
 * @since v1.0
 */
public class XddfScatterChartBuilder extends AbstractMultiXddfChartBuilder {


    private boolean isSmooth = false;

    public XddfScatterChartBuilder() {
        super(ChartTypes.SCATTER);
    }

    @Override
    public void configSeriesTitle(XSSFChart chart, XDDFChartData data, String title, int chartIndex, int index) {
        if (chart.getCTChart().getPlotArea().getScatterChartArray(chartIndex).getSerArray(index).getTx() ==
                null) {
            chart.getCTChart().getPlotArea().getScatterChartArray(chartIndex).getSerArray(index).addNewTx();
            chart.getCTChart().getPlotArea().getScatterChartArray(chartIndex).getSerArray(index).addNewSmooth().setVal(isSmooth);
        }
        data.getSeries().get(index).setTitle(title, null);
    }

    @Override
    public void configChartFeature(XSSFChart chart, XDDFChartData data, int chartIndex) {
        chart.getCTChart().getPlotArea().getScatterChartArray(chartIndex).addNewAxId().setVal(chart.getAxis().get(0).getId());
        chart.getCTChart().getPlotArea().getScatterChartArray(chartIndex).addNewAxId().setVal(chart.getAxis().get(1).getId());

    }

    public boolean isSmooth() {
        return isSmooth;
    }

    public void setSmooth(boolean smooth) {
        isSmooth = smooth;
    }


    public static void main(String[] args) throws IOException {

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("barchart");
        final int NUM_OF_ROWS = 7;
        final int NUM_OF_COLUMNS = 10;

        // Create a row and put some cells in it. Rows are 0 based.
        Row row;
        Cell cell;
        row = sheet.createRow(0);

        for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
            cell = row.createCell((short) colIndex);
            cell.setCellValue(colIndex);
        }

        for (int rowIndex = 1; rowIndex < NUM_OF_ROWS; rowIndex++) {
            row = sheet.createRow((short) rowIndex);
            for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                cell = row.createCell((short) colIndex);
                cell.setCellValue(new Random().nextInt(100));
            }
        }
        LinkedList<SerieConfig> list = new LinkedList<>();
        list.add(new RowSerieConfig("数据1", 1, 0, 9));
        list.add(new RowSerieConfig("数据2", 2, 0, 9));
        list.add(new RowSerieConfig("数据3", 3, 0, 9));
        list.add(new RowSerieConfig("数据4", 4, 0, 9));
        list.add(new RowSerieConfig("数据5", 5, 0, 9));
        list.add(new RowSerieConfig("数据6", 6, 0, 9));
        XddfScatterChartBuilder builder = new XddfScatterChartBuilder();
        builder.buildChart(sheet, "123456",
                new ChartAnchor(0, 5, 20, 9),
                new RowSerieConfig("横坐标", 0, 0, 9),
                list);

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream("ooxml-scatter-chart2.xlsx")) {
            wb.write(fileOut);
        }
    }
}

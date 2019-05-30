package com.assessment.reports.manager.detailedreports;

import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.ui.TextAnchor;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

public class ColorCustomizer  implements JRChartCustomizer {

	@Override
	public void customize(JFreeChart chart, JRChart arg1) {
		// TODO Auto-generated method stub
		CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();

        org.jfree.data.category.CategoryDataset catDS = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < catDS.getRowCount(); i++) {
        	Color seriesColor = new Color(100, 150 +(10*i), 175);
        	 renderer.setSeriesPaint(catDS.getRowIndex(catDS.getRowKey(i)),seriesColor);
        }
//        BarRenderer barRenderer = (BarRenderer) renderer;
//        barRenderer.setItemMargin(5);
//        barRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//        barRenderer.setBaseItemLabelsVisible(true);
//        ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.INSIDE12,
//                TextAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT,
//                -Math.PI / 2.0);
//        barRenderer.setBaseItemLabelPaint(new Color(255, 255, 255));
//        barRenderer.setBasePositiveItemLabelPosition(p);
//
//        ItemLabelPosition p2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
//                TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -Math.PI / 2.0);
//        barRenderer.setBaseItemLabelPaint(new Color(100, 100, 100));
//        barRenderer.setPositiveItemLabelPositionFallback(p2);
//
//       CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
//        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        
	}

}

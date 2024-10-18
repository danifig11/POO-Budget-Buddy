package com.budget.buddy;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graficas extends JPanel {
    private Usuario usuario;

    public Graficas(Usuario usuario) {
        this.usuario = usuario;
        setLayout(new BorderLayout());
        setBackground(new Color(60, 63, 65));
        JFreeChart chart = crearGraficaIngresosEgresos();
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    private JFreeChart crearGraficaIngresosEgresos() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Articulo> articulos = usuario.getArticulos();
        for (Articulo articulo : articulos) {
            dataset.addValue(articulo.getPrecio(), "Precio", articulo.getNombre());
        }
        return ChartFactory.createBarChart("Ingresos y Egresos", "Artículo", "Monto", dataset, PlotOrientation.VERTICAL, true, true, false);
    }
}

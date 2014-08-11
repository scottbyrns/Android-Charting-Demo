package com.scottbyrns.rachiograph;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.scottbyrns.rachiograph.layout.CalendarGraphFragment;
import com.shinobicontrols.charts.ColumnSeries;
import com.shinobicontrols.charts.ColumnSeriesStyle;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.NumberRange;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;
import com.shinobicontrols.charts.TickMark;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class CalendarGraphFragmentHolder extends Fragment implements View.OnClickListener {

    private ShinobiChart shinobiChart;
    private View rootView;

    private static Calendar minutesCalendar = Calendar.getInstance();
    private static Calendar useCalendar = Calendar.getInstance();

    public CalendarGraphFragmentHolder() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_calendar_graph, container, false);

        loadChart(R.id.daily_minutes_watering, R.id.daily_minutes_month, minutesCalendar);
        loadChart(R.id.daily_water_use, R.id.daily_water_use_month, useCalendar);
        setClickHandlers();


        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.previous_month_daily_minutes:
                minutesCalendar.roll(Calendar.MONTH, false);
                loadChart(R.id.daily_minutes_watering, R.id.daily_minutes_month, minutesCalendar);
                break;
            case R.id.next_month_daily_minutes:
                minutesCalendar.roll(Calendar.MONTH, true);
                loadChart(R.id.daily_minutes_watering, R.id.daily_minutes_month, minutesCalendar);
                break;

            case R.id.previous_month_daily_water_use:
                useCalendar.roll(Calendar.MONTH, false);
                loadChart(R.id.daily_water_use, R.id.daily_water_use_month, useCalendar);
                break;

            case R.id.next_month_daily_water_use:
                useCalendar.roll(Calendar.MONTH, true);
                loadChart(R.id.daily_water_use, R.id.daily_water_use_month, useCalendar);
                break;


        }
    }

    private void setClickHandlers() {

        ((ImageButton) rootView.findViewById(R.id.previous_month_daily_minutes)).setOnClickListener(this);
        ((ImageButton) rootView.findViewById(R.id.next_month_daily_minutes)).setOnClickListener(this);
        ((ImageButton) rootView.findViewById(R.id.previous_month_daily_water_use)).setOnClickListener(this);
        ((ImageButton) rootView.findViewById(R.id.next_month_daily_water_use)).setOnClickListener(this);


    }


    private void loadChart(int id, int legendId, Calendar calendar) {


        CalendarGraphFragment chart = getChartFragmentById(id);

        shinobiChart = chart.getShinobiChart();


        // License
        shinobiChart.setLicenseKey("ZcgaU/3wMBgySlbMjAxNDA5MDVjb250YWN0QHNjb3R0YnlybnMuY29tpkOBTHApX4GOnYk0FYJS3PIBs/ZUwoXyjV5SbpkCc5uTFM1cB60vSQoi2tb/m4bx0wzS550a4PTVE0RWSuq32dEj7Ktz1OqIF62GLTKO/LXgi9s/TeWz6itgV7r6EIiAMEqcO+Rkgiqgo1IdyNfDfQpnmULc=BQxSUisl3BaWf/7myRmmlIjRnMU2cA7q+/03ZX9wdj30RzapYANf51ee3Pi8m2rVW6aD7t6Hi4Qy5vv9xpaQYXF5T7XzsafhzS3hbBokp36BoJZg8IrceBj742nQajYyV7trx5GIw9jy/V6r0bvctKYwTim7Kzq+YPWGMtqtQoU=PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");


        // Create the axes and add them to the chart
        NumberAxis xAxis = new NumberAxis();
        xAxis.setDefaultRange(new NumberRange(0.0, 14.0));


        xAxis.getStyle().getTickStyle().setMajorTicksShown(false);
        xAxis.getStyle().getTickStyle().setMinorTicksShown(false);
        xAxis.getStyle().getTickStyle().setLabelColor(Color.GRAY);
        xAxis.getStyle().getTickStyle().setTickGap(0.0f);

        xAxis.setMajorTickFrequency(2.0);
        xAxis.setCurrentDisplayedRangePreservedOnUpdate(true);
        xAxis.setTickMarkClippingModeHigh(TickMark.ClippingMode.TICKS_AND_LABELS_PERSIST);

        shinobiChart.setXAxis(xAxis);
        shinobiChart.getXAxis().enableGesturePanning(true);
        shinobiChart.getXAxis().enableMomentumPanning(true);


        NumberAxis yAxis = new NumberAxis();

        yAxis.getStyle().getTickStyle().setMajorTicksShown(false);
        yAxis.getStyle().getTickStyle().setMinorTicksShown(false);
        yAxis.getStyle().getTickStyle().setLabelColor(Color.GRAY);


        yAxis.setMajorTickFrequency(25.0);
        yAxis.setTickMarkClippingModeHigh(TickMark.ClippingMode.TICKS_PERSIST);
        yAxis.setDefaultRange(new NumberRange(0.0, 125.0));


        shinobiChart.setYAxis(yAxis);


        shinobiChart.getStyle().setPlotAreaBackgroundColor(Color.WHITE);

        shinobiChart.getXAxis().getStyle().setLineWidth(1.f);
        shinobiChart.getXAxis().getStyle().setLineColor(Color.argb(50, 0, 0, 0));


        // Populate with fake data.

        int monthMaxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMMM");
        String month_name = month_date.format(calendar.getTime());

        ((TextView) rootView.findViewById(legendId)).setText(month_name);

        SimpleDataAdapter<Float, Float> dataAdapter1 = new SimpleDataAdapter<Float, Float>();

        for (int i = 0; i < monthMaxDays; i += 1) {

            dataAdapter1.add(new DataPoint<Float, Float>((float) i, (float) Math.random() * 100));

        }


        ColumnSeries series1 = new ColumnSeries();
        series1.setDataAdapter(dataAdapter1);

        // Clear the previous series from the chart.
        if (!shinobiChart.getSeries().isEmpty()) {
            shinobiChart.removeSeries(shinobiChart.getSeries().get(0));
        }

        shinobiChart.addSeries(series1);

        ColumnSeriesStyle style = series1.getStyle();


        style.setLineColor(Color.argb(255, 0x2A, 0x9C, 0xEB));
        style.setLineWidth(90.f);


    }


    private CalendarGraphFragment getChartFragmentById(int id) {

        CalendarGraphFragment chartFragment =
                (CalendarGraphFragment) getFragmentManager().findFragmentById(id);

        return chartFragment;

    }

}
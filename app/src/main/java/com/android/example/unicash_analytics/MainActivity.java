package com.android.example.unicash_analytics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("graphdata");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        reference.addValueEventListener(new ValueEventListener(){
            @Override

            public void onDataChange(DataSnapshot dataSnapshot){


                List<PieEntry> pieEntries = new ArrayList<>();
                List<BarEntry> barEntries = new ArrayList<>();
                final List<String> xAxisLabel = new ArrayList<>();

                for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                    PointValue pointValue = myDataSnapshot.getValue(PointValue.class);
                    int count = (int) dataSnapshot.getChildrenCount();
                    Log.d(TAG, "Graphdata count: "+count);
                    int i=0;


                    pieEntries.add(new PieEntry(pointValue.getAmount(), pointValue.getExpense()));
                    barEntries.add(new BarEntry((float)pointValue.getIndex(), (float) pointValue.getAmount()));
                    xAxisLabel.add(pointValue.getExpense());


                    PieDataSet dataSet = new PieDataSet(pieEntries, "Expense Analytics");
                    BarDataSet barDataSet = new BarDataSet(barEntries, "Amount Spent");

                    dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                    barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                    barDataSet.setBarShadowColor(394858);

                    PieData data = new PieData(dataSet);
                    BarData barData = new BarData(barDataSet);
                    PieChart chart = (PieChart) findViewById(R.id.chart);
                    HorizontalBarChart horizontalBarChart = (HorizontalBarChart) findViewById(R.id.horizontalbar_chart);

                    horizontalBarChart.setDrawBarShadow(true);
                    Description mDescription = new Description();
                    mDescription.setText("Amount Spent for this month.");
                    horizontalBarChart.setDescription(mDescription);
                    horizontalBarChart.setDrawValueAboveBar(true);

                    XAxis XAxis = horizontalBarChart.getXAxis();
                    XAxis.setDrawGridLines(false);
                    XAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
                    XAxis.getValueFormatter();
                    XAxis.setPosition(XAxisPosition.BOTTOM);
                    XAxis.setEnabled(true);
                    XAxis.setDrawAxisLine(true);

                    YAxis YaxisL = horizontalBarChart.getAxisLeft();
                    YaxisL.setAxisMinimum(1000f);
                    YaxisL.setAxisMaximum(100000f);
                    YaxisL.setEnabled(false);

                    YAxis YAxisR = horizontalBarChart.getAxisRight();
                    YAxisR.setDrawAxisLine(true);
                    YAxisR.setDrawGridLines(false);
                    YAxisR.setEnabled(true);

                    horizontalBarChart.setData(barData);
                    horizontalBarChart.setPinchZoom(false);
                    horizontalBarChart.setDoubleTapToZoomEnabled(false);
                    horizontalBarChart.disableScroll();
                    horizontalBarChart.animateXY(2000, 2000);
                    horizontalBarChart.setFitBars(true);
                    horizontalBarChart.invalidate();

                    Description chartDescription = new Description();
                    chartDescription.setText("Pie Chart");
                    chart.setDescription(chartDescription);
                    chart.setData(data);
                    chart.animateY(1000);
                    chart.invalidate();

                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError){
            }


        });
    }




}

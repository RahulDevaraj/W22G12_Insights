package com.example.insights.ui.home;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.insights.databases.UserDatabase;
import com.example.insights.databinding.FragmentHomeBinding;
import com.example.insights.helpers.ChartHelper;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private PieChart pieChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textViewCurrentLimit = binding.textViewCurrentLimit;
        TextView textViewMessage = binding.textViewMessage;
        TextView textViewAmountLeft = binding.textViewAmountLeft;

        pieChart = binding.chart;


        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);

        String dateRegex = month+"-%-"+year;


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String emailId = sharedPreferences.getString("USEREMAIL",null);
        String userLimit = sharedPreferences.getString("USERLIMIT",null);

        textViewCurrentLimit.setText("The currently set Monthly Limit is $"+userLimit);


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            try{

                UserDatabase database = Room.databaseBuilder(getContext(),UserDatabase.class,"User.db").build();
                List<ChartHelper> chartData = database.userTransactionDao().getDataForChart(emailId,dateRegex);
                Double spending = database.userTransactionDao().getMonthlySpending(emailId,dateRegex);

                Log.d("TAG",""+chartData.size());
                getActivity().runOnUiThread(()->{
                    if(spending==null)
                        textViewAmountLeft.setText("Amount left in the budget is $"+String.format("%-10.2f",(Double.parseDouble(userLimit))));
                    else
                    if(Double.parseDouble(userLimit)>=spending)
                    textViewAmountLeft.setText("Amount left in the budget is $"+String.format("%-10.2f",(Double.parseDouble(userLimit)-spending)));
                    else
                        textViewAmountLeft.setText("Spending exceeded budget by $"+String.format("%-10.2f",(spending)-Double.parseDouble(userLimit)));

                    if (chartData.size() < 1) {
                        pieChart.setVisibility(View.INVISIBLE);
                        textViewMessage.setVisibility(View.VISIBLE);
                        textViewMessage.setTypeface(null, Typeface.BOLD);
                        textViewMessage.setTextColor(Color.RED);
                    }
                    else
                    {   pieChart.setVisibility(View.VISIBLE);
                        textViewMessage.setVisibility(View.INVISIBLE);
                        setupPieChart("Monthly Expenses");
                        loadPieChartData(addPieData(chartData));

                        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                            @Override
                            public void onValueSelected(Entry e, Highlight h) {
                                Log.d("TAG",""+e.getY());
                                pieChart.setCenterTextSize(20f);
                                pieChart.setCenterText(String.format("%s%.2f","$",e.getY()));
                            }

                            @Override
                            public void onNothingSelected() {

                            }
                        });
                    }


                });





            }
            catch(Exception e){
                e.printStackTrace();

            }
        });

        return root;
    }
    private void setupPieChart(String centerText) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(16);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText(centerText);
        pieChart.setCenterTextSize(18);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setWordWrapEnabled(true);
        l.setTextSize(16);
        l.setDrawInside(false);
        l.setEnabled(true);
    }
    private void loadPieChartData(ArrayList<PieEntry> arrayList) {
        ArrayList<PieEntry> entries = new ArrayList<>(arrayList);


        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        //data.setValueFormatter(new PercentFormatter());
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(16f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
    private ArrayList<PieEntry> addPieData(List<ChartHelper> chartData){
        ArrayList<PieEntry> entries = new ArrayList<>();

        for(int i=0;i<chartData.size();i++){
            PieEntry pieEntry = new PieEntry(Float.parseFloat(chartData.get(i).getSum().toString()),chartData.get(i).getCategory());
            entries.add(pieEntry);
        }
        return entries;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
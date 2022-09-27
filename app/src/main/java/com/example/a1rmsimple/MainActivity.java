package com.example.a1rmsimple;

import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TODO should this be the gridview? "Data to display"
    //TODO Should these Strings be ints or will that cause a type error?
    private final List<String> mCalculation = new ArrayList<>();
    private final List<Integer> mPercentList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecycleViewAdapter mAdapter;

    // the big textview
    private TextView calculation;
    // the small textview
    private TextView percent;
    private EditText one_rm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        calculation = findViewById(R.id.rep_weight);
        one_rm = findViewById(R.id.enter_max);
        percent = findViewById(R.id.rep_percent);

        // list of percentages to display under each grid-item: from 70% to 110%
        int start = 70;
        for (int i = 0; i <= 8; i++) {
            mPercentList.add(start);
            start += 5;
        }

        System.out.println(mPercentList);

        for (int j = 0; j <= 8; j++){
            mCalculation.add("0");
        }

        System.out.println("mCalc" + mCalculation);

        mRecyclerView = findViewById(R.id.recyclerWeight);
//        mRecyclerView.addItemDecoration(new GridItemDecoration(arg1, arg2));
        mAdapter = new RecycleViewAdapter(this, mCalculation, mPercentList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    // Method that calculates the percentage to be displayed in the gridItems
    public int doBasicMath(int percentageOfMax, int maxWeight){
        System.out.println("Percentage calculation");
        return (maxWeight * percentageOfMax)/100;
    }

        //TODO change to ontouchlistener. Does this click need to be handled in adapter?
        //TODO  separate the data/caluculation to another class
    public void updateWeightEditText(View view){
        if (!one_rm.getText().toString().equals("")){
            mCalculation.clear();
            int k = 0;
            for (int i = 0; i <= mPercentList.size()-1; i++){
                mCalculation.add(String.valueOf(doBasicMath(mPercentList.get(k), Integer.parseInt(one_rm.getText().toString()))));
                k++;
            }

            System.out.println("mcalc in update" + mCalculation);

            mAdapter.notifyDataSetChanged();
            //TODO update values/reinflate view - calculation.setText(String.valueOf(mCalculation));
            System.out.println("ET updated");
        }

    }

}

/* TODO

UI:
- change icon: tiny man lifts weights when input has been entered
- ItemDecoration - look into implementation

Logic:
- add savestate (SharedPreferences should be enough)
- data in each column should update as I input and delete data from max_weight edittext
- round up calculation



*/

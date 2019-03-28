package com.example.jiabo.custombottondialogtest;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        showDialog();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this);
        adapter.fillList(list);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = layoutManager.findFirstVisibleItemPosition();
                adapter.setSecondPosition(position);
                Log.d(TAG, "onScrolled: " + position);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private void showDialog() {
        AlertDialog.Builder customDialog = new AlertDialog.Builder(MainActivity.this);
        final View view = LinearLayout.inflate(this, R.layout.item_botton_dialog, null);
        initRecyclerView(view);
        customDialog.create();
        customDialog.setView(view);
        customDialog.show();
    }

    private void initData() {
        list.add("text1");
        list.add("text2");
        list.add("text3");
        list.add("text4");
    }
}

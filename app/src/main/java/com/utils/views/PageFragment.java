package com.utils.views;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.shizhefei.fragment.LazyFragment;
import com.stone.beautyshare.R;
import com.stone.beautyshare.config.ClassifyConfig;
import com.stone.beautyshare.views.widget.GridViewAdapter;
import com.stone.beautyshare.ViewActivity;

public class PageFragment extends LazyFragment {
    private GridView gridView;
    private int tabIndex;
    private Context context;
    public static final String INTENT_INT_INDEX = "intent_int_index";

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tabmain_item);
        context = getApplicationContext();
        tabIndex =getArguments().getInt(INTENT_INT_INDEX);
        gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new GridViewAdapter(context, ClassifyConfig.getClassifies().get(tabIndex).photos));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putInt("ClassifyIndex", tabIndex);
//                bundle.putInt("Hotzone", c.d(this.c));
                bundle.putInt("PhotosIndex", position);
                Intent intent = new Intent(context, ViewActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyViewLazy() {
        super.onDestroyViewLazy();
    }

}

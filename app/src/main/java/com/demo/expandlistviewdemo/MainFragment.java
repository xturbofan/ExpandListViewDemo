package com.demo.expandlistviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private ExpandableListView expandableListView;
    private List<GrandFather> grandFatherList;
    private FirstAdapter mAdapter;
    private RelativeLayout mSearchRealtive;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater);
    }

    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        initFindViewById(view);
        initData();
        return view;
    }

    protected void initFindViewById(View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
    }

    protected void initData() {
        grandFatherList = new ArrayList<>();
        for (int j = 0; j < 6; j++) {
            GrandFather grandFather = new GrandFather();
            grandFather.cname = "Group_" + j;
            List<GrandFather.Father> fatherList = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                GrandFather.Father father = new GrandFather.Father();
                father.setId(i + "");
                father.setName("G_" + j + "_Node_" + i);
                List<GrandFather.Son> sonList = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    GrandFather.Son son = new GrandFather.Son();
                    son.setName("G_" + j + "_Node_" + i + "_Son_" + k);
                    sonList.add(son);
                }
                father.setSonList(sonList);
                fatherList.add(father);
            }
            grandFather.setFatherList(fatherList);
            grandFatherList.add(grandFather);
        }
        fillData();
    }

    private void fillData() {
        mAdapter = new FirstAdapter(getContext(), grandFatherList);
        expandableListView.setAdapter(mAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < expandableListView.getExpandableListAdapter().getGroupCount(); i++) {
                    if (expandableListView.isGroupExpanded(i) && i != groupPosition) {
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });
        expandableListView.expandGroup(0);
    }
}

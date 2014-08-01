package com.techtalk4geeks.datacalc;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CFragment extends Fragment {
	StartActivity start = new StartActivity();
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	start.calculateTotal();
        return inflater.inflate(R.layout.cfragment, container, false);
    }
    
}


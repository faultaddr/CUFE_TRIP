package com.example.panda.myapplication.Ui.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.panda.myapplication.R;

public class FirstFragment extends Fragment {
	public FirstFragment(){

	}

	public static FirstFragment newInstance(){
		FirstFragment firstFragment=new FirstFragment();

		return firstFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.view1, container, false);
	}
}



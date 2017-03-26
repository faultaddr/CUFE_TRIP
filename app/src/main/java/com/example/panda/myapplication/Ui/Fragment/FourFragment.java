package com.example.panda.myapplication.Ui.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
		import android.view.LayoutInflater;
		import android.view.View;
		import android.view.ViewGroup;

import com.example.panda.myapplication.R;

public class FourFragment extends Fragment {

	public FourFragment(){

	}

	public static FourFragment newInstance(){
		FourFragment fourFragment=new FourFragment();

		return fourFragment;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.view4, container, false);
	}
}
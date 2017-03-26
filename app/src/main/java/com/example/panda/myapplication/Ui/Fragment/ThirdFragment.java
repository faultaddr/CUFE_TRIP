package com.example.panda.myapplication.Ui.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
		import android.view.LayoutInflater;
		import android.view.View;
		import android.view.ViewGroup;

import com.example.panda.myapplication.R;

public class ThirdFragment extends Fragment {
	public ThirdFragment(){

	}

	public static ThirdFragment newInstance(){
		ThirdFragment thirdFragment=new ThirdFragment();

		return thirdFragment;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.view3, container, false);
	}
}

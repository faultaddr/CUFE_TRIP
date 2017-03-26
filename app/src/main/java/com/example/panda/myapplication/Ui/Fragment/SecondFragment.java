package com.example.panda.myapplication.Ui.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.panda.myapplication.R;

public class SecondFragment extends Fragment {
	public SecondFragment(){

	}

	public static SecondFragment newInstance(){
		SecondFragment secondFragment=new SecondFragment();

		return secondFragment;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.view2, container, false);
	}
}

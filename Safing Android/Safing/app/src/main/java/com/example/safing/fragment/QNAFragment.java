package com.example.safing.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.safing.MainActivity;
import com.example.safing.R;
public class QNAFragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    MainActivity mainActivity = new MainActivity();


    public QNAFragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_qna, container, false);

        return rootView;
    }
}
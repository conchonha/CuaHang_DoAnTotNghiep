package com.example.cuahang_doan.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuahang_doan.R;

public class Fragment_timkiem extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=View.inflate(getActivity(),R.layout.fragment_timkiem,null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

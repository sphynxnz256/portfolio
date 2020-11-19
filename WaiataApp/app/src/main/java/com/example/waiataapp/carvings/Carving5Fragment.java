package com.example.waiataapp.carvings;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.waiataapp.R;

public class Carving5Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carving5, container, false);

        //turn info link into a clickable link
        TextView infoLink = view.findViewById(R.id.text_info_link);
        infoLink.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}

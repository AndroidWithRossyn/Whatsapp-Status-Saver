package com.Udaicoders.wawbstatussaver.font.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.font.adapter.PrettifyAdapter;
import com.Udaicoders.wawbstatussaver.font.model.Font;

import java.util.ArrayList;

public class PrettifyFragment extends Fragment {


    public PrettifyFragment() {
        // Required empty public constructor
    }

    private Activity context;
    private RecyclerView fontsRV;
    private ArrayList<Font> decorationFonts = new ArrayList<>();
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_fancy_font, container, false);

        if (decorationFonts.isEmpty()) {
            for (int i = 0; i < 98; i++) {
                decorationFonts.add(new Font());
            }
        } else {
            decorationFonts.clear();
            for (int i = 0; i < 98; i++) {
                decorationFonts.add(new Font());
            }
        }

        fontsRV = view.findViewById(R.id.recycle_view_FF);

        final PrettifyAdapter adapter = new PrettifyAdapter(decorationFonts, context);
        fontsRV.setLayoutManager(new LinearLayoutManager(context));
        fontsRV.setAdapter(adapter);

        editText = view.findViewById(R.id.edit_text_FF);
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editTextStr = editText.getText().toString();

                if (editTextStr.isEmpty()) {
                    editTextStr = "Fancy Font";
                }

                for (int item = 0; item < decorationFonts.size(); item++) {
                    decorationFonts.get(item).setPreviewText(editTextStr);
                    adapter.notifyDataSetChanged();


                }
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }


}

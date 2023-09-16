package com.Udaicoders.wawbstatussaver.font.adapter;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.font.model.Font;

import java.util.ArrayList;


public class FontAdapter extends RecyclerView.Adapter<FontAdapter.MyViewHolder> {
    private ArrayList<Font> fontItems;
    private Activity activity;

    @Override
    @NonNull
    public FontAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(activity).inflate(R.layout.adapter_font, parent, false);
        return new MyViewHolder(row);
    }

    public FontAdapter(ArrayList<Font> fontItems, Activity activity) {
        this.fontItems = fontItems;
        this.activity = activity;

    }

    @Override
    public void onBindViewHolder(@NonNull final FontAdapter.MyViewHolder holder, final int position) {
        final Font f = fontItems.get(position);
        StringBuilder strBld = new StringBuilder(f.getPreviewText());
        switch (position) {
            case 0:
                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharFirst(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 1:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharSecond(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 2:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharThird(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;
            case 3:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharFourth(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 4:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharFifth(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;
            case 5:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharSixth(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 6:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharSeventh(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 7:
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♥');
                        }


                    }

                } else if (!f.getPreviewText().contains("♥")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♥');
                        }


                    }
                }
                break;


            case 8:

                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (charOne == 0) {
                            strBld.insert(charOne, '【');
                            ++charOne;
                        }


                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '】');
                            strBld.insert(++charOne, '【');


                        }

                        if (strBld.charAt(charOne) == ' ') {
                            strBld.deleteCharAt(--charOne);
                            strBld.insert(++charOne, '【');
                        }

                        if (strBld.length() - 1 == charOne) {
                            strBld.deleteCharAt(charOne);
                        }

                    }
                } else if (!f.getPreviewText().contains("【") && !f.getPreviewText().contains("】")) {

                    for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {

                        if (charOne == 0) {
                            strBld.insert(charOne, '【');
                            ++charOne;
                        }

                        if (strBld.length() - 1 != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '】');
                            strBld.insert(++charOne, '【');


                        } else if (strBld.length() - 1 == charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '】');
                        }

                        if (strBld.charAt(charOne) == ' ' && charOne != 0) {
                            strBld.deleteCharAt(--charOne);
                            strBld.insert(++charOne, '【');
                        } else if (strBld.length() - 1 == charOne && strBld.charAt(charOne) == ' ' && charOne != 0) {
                            strBld.deleteCharAt(++charOne);
                        }

                    }

                }

                break;

            case 9:
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☆');
                        }


                    }

                } else if (!f.getPreviewText().contains("☆")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☆');
                        }


                    }
                }
                break;

            case 10:
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❦');
                        }


                    }

                } else if (!f.getPreviewText().contains("❦")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❦');
                        }


                    }
                }
                break;
            case 11:
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❄');
                        }


                    }

                } else if (!f.getPreviewText().contains("❄")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❄');
                        }


                    }
                }
                break;

            case 12 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '✾');
                        }


                    }

                } else if (!f.getPreviewText().contains("✾")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '✾');
                        }


                    }
                }
                break;
            case 13 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☀');
                        }


                    }

                } else if (!f.getPreviewText().contains("☀")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☀');
                        }


                    }
                }
                break;

            case 14 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☃');
                        }


                    }

                } else if (!f.getPreviewText().contains("☃")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☃');
                        }


                    }
                }
                break;

            case 15 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❤');
                        }


                    }

                } else if (!f.getPreviewText().contains("❤")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❤');
                        }


                    }
                }
                break;

            case 16 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☘');
                        }


                    }

                } else if (!f.getPreviewText().contains("☘")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☘');
                        }


                    }
                }
                break;

            case 17 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☕');
                        }


                    }

                } else if (!f.getPreviewText().contains("☕")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☕');
                        }


                    }
                }
                break;

            case 18 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☝');
                        }


                    }

                } else if (!f.getPreviewText().contains("☝")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☝');
                        }


                    }
                }
                break;

            case 19 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❁');
                        }


                    }

                } else if (!f.getPreviewText().contains("❁")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❁');
                        }


                    }
                }
                break;



            case 20 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♈');
                        }


                    }

                } else if (!f.getPreviewText().contains("♈")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♈');
                        }


                    }
                }
                break;

            case 21 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♉');
                        }


                    }

                } else if (!f.getPreviewText().contains("♉")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♉');
                        }


                    }
                }
                break;

            case 22 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♊');
                        }


                    }

                } else if (!f.getPreviewText().contains("♊")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♊');
                        }


                    }
                }
                break;

            case 23 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♋');
                        }


                    }

                } else if (!f.getPreviewText().contains("♋")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♋');
                        }


                    }
                }
                break;

            case 24 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♌');
                        }


                    }

                } else if (!f.getPreviewText().contains("♌")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♌');
                        }


                    }
                }
                break;

            case 25 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♍');
                        }


                    }

                } else if (!f.getPreviewText().contains("♍")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♍');
                        }


                    }
                }
                break;


            case 26 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♎');
                        }


                    }

                } else if (!f.getPreviewText().contains("♎")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♎');
                        }


                    }
                }
                break;

            case 27 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♏');
                        }


                    }

                } else if (!f.getPreviewText().contains("♏")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♏');
                        }


                    }
                }
                break;


            case 28 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♐');
                        }


                    }

                } else if (!f.getPreviewText().contains("♐")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♐');
                        }


                    }
                }
                break;

            case 29 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♑');
                        }


                    }

                } else if (!f.getPreviewText().contains("♑")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♑');
                        }


                    }
                }
                break;
            case 30 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♒');
                        }


                    }

                } else if (!f.getPreviewText().contains("♒")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♒');
                        }


                    }
                }
                break;
            case 31 :
                if ("Fancy Font".equals(f.getPreviewText())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♓');
                        }


                    }

                } else if (!f.getPreviewText().contains("♓")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♓');
                        }


                    }
                }
                break;



        }
        f.setPreviewText(strBld.toString());
        holder.description.setText(f.getPreviewText());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
                String desStr = holder.description.getText().toString();
                Toast.makeText(activity, "Copied to clipboard! Your copied text is " + desStr, Toast.LENGTH_SHORT).show();
                ClipData clip = ClipData.newPlainText("simple text", desStr);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                }


            }
        });
    }


    private char getSpecialCharFirst(char a) {
        char ch = a;

        if (ch == 'A') {
            ch = 'Ⓐ';
        } else if (ch == 'B') {
            ch = 'Ⓑ';
        } else if (ch == 'C') {
            ch = 'Ⓒ';
        } else if (ch == 'D') {
            ch = 'Ⓓ';
        } else if (ch == 'E') {
            ch = 'Ⓔ';
        } else if (ch == 'F') {
            ch = 'Ⓕ';
        } else if (ch == 'G') {
            ch = 'Ⓖ';
        } else if (ch == 'H') {
            ch = 'Ⓗ';
        } else if (ch == 'I') {
            ch = 'Ⓘ';
        } else if (ch == 'J') {
            ch = 'Ⓙ';
        } else if (ch == 'K') {
            ch = 'Ⓚ';
        } else if (ch == 'L') {
            ch = 'Ⓛ';
        } else if (ch == 'M') {
            ch = 'Ⓜ';
        } else if (ch == 'N') {
            ch = 'Ⓝ';
        } else if (ch == 'O') {
            ch = 'Ⓞ';
        } else if (ch == 'P') {
            ch = 'Ⓟ';
        } else if (ch == 'Q') {
            ch = 'Ⓠ';
        } else if (ch == 'R') {
            ch = 'Ⓡ';
        } else if (ch == 'S') {
            ch = 'Ⓢ';
        } else if (ch == 'T') {
            ch = 'Ⓣ';
        } else if (ch == 'U') {
            ch = 'Ⓤ';
        } else if (ch == 'V') {
            ch = 'Ⓥ';
        } else if (ch == 'W') {
            ch = 'Ⓦ';
        } else if (ch == 'X') {
            ch = 'Ⓧ';
        } else if (ch == 'Y') {
            ch = 'Ⓨ';
        } else if (ch == 'Z') {
            ch = 'Ⓩ';
        } else if (ch == 'a') {
            ch = 'ⓐ';
        } else if (ch == 'b') {
            ch = 'ⓑ';
        } else if (ch == 'c') {
            ch = 'ⓒ';
        } else if (ch == 'd') {
            ch = 'ⓓ';
        } else if (ch == 'e') {
            ch = 'ⓔ';
        } else if (ch == 'f') {
            ch = 'ⓕ';
        } else if (ch == 'g') {
            ch = 'ⓖ';
        } else if (ch == 'h') {
            ch = 'ⓗ';
        } else if (ch == 'i') {
            ch = 'ⓘ';
        } else if (ch == 'j') {
            ch = 'ⓙ';
        } else if (ch == 'k') {
            ch = 'ⓚ';
        } else if (ch == 'l') {
            ch = 'ⓛ';
        } else if (ch == 'm') {
            ch = 'ⓜ';
        } else if (ch == 'n') {
            ch = 'ⓝ';
        } else if (ch == 'o') {
            ch = 'ⓞ';
        } else if (ch == 'p') {
            ch = 'ⓟ';
        } else if (ch == 'q') {
            ch = 'ⓠ';
        } else if (ch == 'r') {
            ch = 'ⓡ';
        } else if (ch == 's') {
            ch = 'ⓢ';
        } else if (ch == 't') {
            ch = 'ⓣ';
        } else if (ch == 'u') {
            ch = 'ⓤ';
        } else if (ch == 'v') {
            ch = 'ⓥ';
        } else if (ch == 'w') {
            ch = 'ⓦ';
        } else if (ch == 'x') {
            ch = 'ⓧ';
        } else if (ch == 'y') {
            ch = 'ⓨ';
        } else if (ch == 'z') {
            ch = 'ⓩ';
        } else if (ch == '0') {
            ch = '⓪';
        } else if (ch == '1') {
            ch = '①';
        } else if (ch == '2') {
            ch = '②';
        } else if (ch == '3') {
            ch = '③';
        } else if (ch == '4') {
            ch = '④';
        } else if (ch == '5') {
            ch = '⑤';
        } else if (ch == '6') {
            ch = '⑥';
        } else if (ch == '7') {
            ch = '⑦';
        } else if (ch == '8') {
            ch = '⑧';
        } else if (ch == '9') {
            ch = '⑨';
        }

        return ch;
    }

    private char getSpecialCharSecond(char a) {
        char ch = a;

        if (ch == 'A') {
            ch = '₳';
        } else if (ch == 'B') {
            ch = '฿';
        } else if (ch == 'C') {
            ch = '₵';
        } else if (ch == 'D') {
            ch = 'Đ';
        } else if (ch == 'E') {
            ch = 'Ɇ';
        } else if (ch == 'F') {
            ch = '₣';
        } else if (ch == 'G') {
            ch = '₲';
        } else if (ch == 'H') {
            ch = 'Ⱨ';
        } else if (ch == 'I') {
            ch = 'ł';
        } else if (ch == 'K') {
            ch = '₭';
        } else if (ch == 'L') {
            ch = 'Ⱡ';
        } else if (ch == 'M') {
            ch = '₥';
        } else if (ch == 'N') {
            ch = '₦';
        } else if (ch == 'O') {
            ch = 'Ø';
        } else if (ch == 'P') {
            ch = '₱';
        } else if (ch == 'R') {
            ch = 'Ɽ';
        } else if (ch == 'S') {
            ch = '₴';
        } else if (ch == 'T') {
            ch = '₮';
        } else if (ch == 'U') {
            ch = 'Ʉ';
        } else if (ch == 'W') {
            ch = '₩';
        } else if (ch == 'X') {
            ch = 'Ӿ';
        } else if (ch == 'Y') {
            ch = 'Ɏ';
        } else if (ch == 'Z') {
            ch = 'Ⱬ';
        } else if (ch == 'a') {
            ch = '₳';
        } else if (ch == 'b') {
            ch = '฿';
        } else if (ch == 'c') {
            ch = '₵';
        } else if (ch == 'd') {
            ch = 'Đ';
        } else if (ch == 'e') {
            ch = 'Ɇ';
        } else if (ch == 'f') {
            ch = '₣';
        } else if (ch == 'g') {
            ch = '₲';
        } else if (ch == 'h') {
            ch = 'Ⱨ';
        } else if (ch == 'i') {
            ch = 'ł';
        } else if (ch == 'j') {
            ch = 'J';
        } else if (ch == 'k') {
            ch = '₭';
        } else if (ch == 'l') {
            ch = 'Ⱡ';
        } else if (ch == 'm') {
            ch = '₥';
        } else if (ch == 'n') {
            ch = '₦';
        } else if (ch == 'o') {
            ch = 'Ø';
        } else if (ch == 'p') {
            ch = '₱';
        } else if (ch == 'q') {
            ch = 'Q';
        } else if (ch == 'r') {
            ch = 'Ɽ';
        } else if (ch == 's') {
            ch = '₴';
        } else if (ch == 't') {
            ch = '₮';
        } else if (ch == 'u') {
            ch = 'Ʉ';
        } else if (ch == 'v') {
            ch = 'V';
        } else if (ch == 'w') {
            ch = '₩';
        } else if (ch == 'x') {
            ch = 'Ӿ';
        } else if (ch == 'y') {
            ch = 'Ɏ';
        } else if (ch == 'z') {
            ch = 'Ⱬ';
        }

        return ch;
    }

    private char getSpecialCharThird(char a) {
        char ch = a;

        if (ch == 'A' || ch == 'a') {
            ch = 'Ꭺ';
        } else if (ch == 'B' || ch == 'b') {
            ch = 'b';
        } else if (ch == 'C' || ch == 'c') {
            ch = 'Ꮯ';
        } else if (ch == 'D' || ch == 'd') {
            ch = 'Ꭰ';
        } else if (ch == 'E' || ch == 'e') {
            ch = 'Ꭼ';
        } else if (ch == 'F' || ch == 'f') {
            ch = 'f';
        } else if (ch == 'G' || ch == 'g') {
            ch = 'Ꮆ';
        } else if (ch == 'H' || ch == 'h') {
            ch = 'h';
        } else if (ch == 'I' || ch == 'i') {
            ch = 'Ꭵ';
        } else if (ch == 'J' || ch == 'j') {
            ch = 'j';
        } else if (ch == 'K' || ch == 'k') {
            ch = 'Ꮶ';
        } else if (ch == 'L' || ch == 'l') {
            ch = 'Ꮮ';
        } else if (ch == 'M' || ch == 'm') {
            ch = 'm';
        } else if (ch == 'N' || ch == 'n') {
            ch = 'Ꮑ';
        } else if (ch == 'O' || ch == 'o') {
            ch = 'Ꮎ';
        } else if (ch == 'P' || ch == 'p') {
            ch = 'Ꮲ';
        } else if (ch == 'Q' || ch == 'q') {
            ch = 'q';
        } else if (ch == 'R' || ch == 'r') {
            ch = 'Ꮢ';
        } else if (ch == 'S' || ch == 's') {
            ch = 's';
        } else if (ch == 'T' || ch == 't') {
            ch = 'Ꮖ';
        } else if (ch == 'U' || ch == 'u') {
            ch = 'u';
        } else if (ch == 'V' || ch == 'v') {
            ch = 'Ꮙ';
        } else if (ch == 'W' || ch == 'w') {
            ch = 'Ꮃ';
        } else if (ch == 'X' || ch == 'x') {
            ch = 'x';
        } else if (ch == 'Y' || ch == 'y') {
            ch = 'Ꮍ';
        } else if (ch == 'Z' || ch == 'z') {
            ch = 'Ꮓ';
        }

        return ch;
    }

    private char getSpecialCharFourth(char a) {
        char ch = a;

        if (ch == 'A') {
            ch = 'Ḁ';
        } else if (ch == 'B') {
            ch = 'Ḃ';
        } else if (ch == 'C') {
            ch = 'Ḉ';
        } else if (ch == 'D') {
            ch = 'Ḋ';
        } else if (ch == 'E') {
            ch = 'Ḕ';
        } else if (ch == 'F') {
            ch = 'Ḟ';
        } else if (ch == 'G') {
            ch = 'Ḡ';
        } else if (ch == 'H') {
            ch = 'Ḧ';
        } else if (ch == 'I') {
            ch = 'Ḭ';
        } else if (ch == 'K') {
            ch = 'Ḳ';
        } else if (ch == 'L') {
            ch = 'Ḷ';
        } else if (ch == 'M') {
            ch = 'Ṁ';
        } else if (ch == 'N') {
            ch = 'Ṇ';
        } else if (ch == 'O') {
            ch = 'Ṏ';
        } else if (ch == 'P') {
            ch = 'Ṗ';
        } else if (ch == 'R') {
            ch = 'Ṙ';
        } else if (ch == 'S') {
            ch = 'Ṡ';
        } else if (ch == 'T') {
            ch = 'Ṯ';
        } else if (ch == 'U') {
            ch = 'Ṳ';
        } else if (ch == 'V') {
            ch = 'Ṽ';
        } else if (ch == 'W') {
            ch = 'Ẇ';
        } else if (ch == 'X') {
            ch = 'Ẍ';
        } else if (ch == 'Y') {
            ch = 'Ẏ';
        } else if (ch == 'Z') {
            ch = 'Ẓ';
        } else if (ch == 'a') {
            ch = 'Ḁ';
        } else if (ch == 'b') {
            ch = 'ḃ';
        } else if (ch == 'c') {
            ch = 'ḉ';
        } else if (ch == 'd') {
            ch = 'Ḋ';
        } else if (ch == 'e') {
            ch = 'ḕ';
        } else if (ch == 'f') {
            ch = 'ḟ';
        } else if (ch == 'g') {
            ch = 'Ḡ';
        } else if (ch == 'h') {
            ch = 'ḧ';
        } else if (ch == 'i') {
            ch = 'ḭ';
        } else if (ch == 'k') {
            ch = 'Ḳ';
        } else if (ch == 'l') {
            ch = 'Ḷ';
        } else if (ch == 'm') {
            ch = 'ṁ';
        } else if (ch == 'n') {
            ch = 'Ṇ';
        } else if (ch == 'o') {
            ch = 'ṏ';
        } else if (ch == 'p') {
            ch = 'Ṗ';
        } else if (ch == 'r') {
            ch = 'ṙ';
        } else if (ch == 's') {
            ch = 'Ṡ';
        } else if (ch == 't') {
            ch = 'Ṯ';
        } else if (ch == 'u') {
            ch = 'ṳ';
        } else if (ch == 'v') {
            ch = 'Ṽ';
        } else if (ch == 'w') {
            ch = 'ẇ';
        } else if (ch == 'x') {
            ch = 'Ẍ';
        } else if (ch == 'y') {
            ch = 'ẏ';
        } else if (ch == 'z') {
            ch = 'Ẓ';
        }

        return ch;
    }

    private char getSpecialCharFifth(char a) {
        char ch = a;

        if (ch == 'A' || ch == 'a') {
            ch = 'α';
        } else if (ch == 'B' || ch == 'b') {
            ch = 'в';
        } else if (ch == 'C' || ch == 'c') {
            ch = '¢';
        } else if (ch == 'D' || ch == 'd') {
            ch = '∂';
        } else if (ch == 'E' || ch == 'e') {
            ch = 'є';
        } else if (ch == 'F' || ch == 'f') {
            ch = 'f';
        } else if (ch == 'G' || ch == 'g') {
            ch = 'g';
        } else if (ch == 'H' || ch == 'h') {
            ch = 'н';
        } else if (ch == 'I' || ch == 'i') {
            ch = 'ι';
        } else if (ch == 'J' || ch == 'j') {
            ch = 'נ';
        } else if (ch == 'K' || ch == 'k') {
            ch = 'к';
        } else if (ch == 'L' || ch == 'l') {
            ch = 'ℓ';
        } else if (ch == 'M' || ch == 'm') {
            ch = 'м';
        } else if (ch == 'N' || ch == 'n') {
            ch = 'и';
        } else if (ch == 'O' || ch == 'o') {
            ch = 'σ';
        } else if (ch == 'P' || ch == 'p') {
            ch = 'ρ';
        } else if (ch == 'Q' || ch == 'q') {
            ch = 'q';
        } else if (ch == 'R' || ch == 'r') {
            ch = 'я';
        } else if (ch == 'S' || ch == 's') {
            ch = 's';
        } else if (ch == 'T' || ch == 't') {
            ch = 'т';
        } else if (ch == 'U' || ch == 'u') {
            ch = 'υ';
        } else if (ch == 'V' || ch == 'v') {
            ch = 'ν';
        } else if (ch == 'W' || ch == 'w') {
            ch = 'ω';
        } else if (ch == 'X' || ch == 'x') {
            ch = 'χ';
        } else if (ch == 'Y' || ch == 'y') {
            ch = 'у';
        } else if (ch == 'Z' || ch == 'z') {
            ch = 'z';
        }

        return ch;
    }

    private char getSpecialCharSixth(char a) {
        char ch = a;

        if (ch == 'A' || ch == 'a') {
            ch = 'Ã';
        } else if (ch == 'B' || ch == 'b') {
            ch = 'β';
        } else if (ch == 'C' || ch == 'c') {
            ch = 'Č';
        } else if (ch == 'D' || ch == 'd') {
            ch = 'Ď';
        } else if (ch == 'E' || ch == 'e') {
            ch = 'Ẹ';
        } else if (ch == 'F' || ch == 'f') {
            ch = 'Ƒ';
        } else if (ch == 'G' || ch == 'g') {
            ch = 'Ğ';
        } else if (ch == 'H' || ch == 'h') {
            ch = 'Ĥ';
        } else if (ch == 'I' || ch == 'i') {
            ch = 'Į';
        } else if (ch == 'J' || ch == 'j') {
            ch = 'Ĵ';
        } else if (ch == 'K' || ch == 'k') {
            ch = 'Ќ';
        } else if (ch == 'L' || ch == 'l') {
            ch = 'Ĺ';
        } else if (ch == 'M' || ch == 'm') {
            ch = 'ϻ';
        } else if (ch == 'N' || ch == 'n') {
            ch = 'Ň';
        } else if (ch == 'O' || ch == 'o') {
            ch = 'Ỗ';
        } else if (ch == 'P' || ch == 'p') {
            ch = 'Ƥ';
        } else if (ch == 'Q' || ch == 'q') {
            ch = 'Ǫ';
        } else if (ch == 'R' || ch == 'r') {
            ch = 'Ř';
        } else if (ch == 'S' || ch == 's') {
            ch = 'Ŝ';
        } else if (ch == 'T' || ch == 't') {
            ch = 'Ť';
        } else if (ch == 'U' || ch == 'u') {
            ch = 'Ǘ';
        } else if (ch == 'V' || ch == 'v') {
            ch = 'ϋ';
        } else if (ch == 'W' || ch == 'w') {
            ch = 'Ŵ';
        } else if (ch == 'X' || ch == 'x') {
            ch = 'Ж';
        } else if (ch == 'Y' || ch == 'y') {
            ch = 'Ў';
        } else if (ch == 'Z' || ch == 'z') {
            ch = 'Ż';
        }

        return ch;
    }

    private char getSpecialCharSeventh(char a) {
        char ch = a;

        if (ch == 'A' || ch == 'a') {
            ch = 'A';
        } else if (ch == 'B' || ch == 'b') {
            ch = 'Ɓ';
        } else if (ch == 'C' || ch == 'c') {
            ch = 'Ƈ';
        } else if (ch == 'D' || ch == 'd') {
            ch = 'Ɗ';
        } else if (ch == 'E' || ch == 'e') {
            ch = 'Є';
        } else if (ch == 'F' || ch == 'f') {
            ch = 'Ƒ';
        } else if (ch == 'G' || ch == 'g') {
            ch = 'Ɠ';
        } else if (ch == 'H' || ch == 'h') {
            ch = 'Ӈ';
        } else if (ch == 'I' || ch == 'i') {
            ch = 'Ɩ';
        } else if (ch == 'J' || ch == 'j') {
            ch = 'ʆ';
        } else if (ch == 'K' || ch == 'k') {
            ch = 'Ƙ';
        } else if (ch == 'L' || ch == 'l') {
            ch = 'Լ';
        } else if (ch == 'M' || ch == 'm') {
            ch = 'M';
        } else if (ch == 'N' || ch == 'n') {
            ch = 'Ɲ';
        } else if (ch == 'O' || ch == 'o') {
            ch = 'Ơ';
        } else if (ch == 'P' || ch == 'p') {
            ch = 'Ƥ';
        } else if (ch == 'Q' || ch == 'q') {
            ch = 'Ƣ';
        } else if (ch == 'R' || ch == 'r') {
            ch = 'Ʀ';
        } else if (ch == 'S' || ch == 's') {
            ch = 'Ƨ';
        } else if (ch == 'T' || ch == 't') {
            ch = 'Ƭ';
        } else if (ch == 'U' || ch == 'u') {
            ch = 'Ʋ';
        } else if (ch == 'V' || ch == 'v') {
            ch = 'Ɣ';
        } else if (ch == 'W' || ch == 'w') {
            ch = 'Ɯ';
        } else if (ch == 'X' || ch == 'x') {
            ch = 'Ҳ';
        } else if (ch == 'Y' || ch == 'y') {
            ch = 'Ƴ';
        } else if (ch == 'Z' || ch == 'z') {
            ch = 'Ȥ';
        }

        return ch;
    }


    @Override
    public int getItemCount() {
        return fontItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        LinearLayout cardView;

        private MyViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.descriptionTV);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}



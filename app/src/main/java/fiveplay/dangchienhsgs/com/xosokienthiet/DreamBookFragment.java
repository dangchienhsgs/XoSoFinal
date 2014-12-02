package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.DreamBookAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.DreamBookReader;

public class DreamBookFragment extends Fragment {
    private final String TAG = "Dream Book Activity";

    private ListView listView;
    private EditText editSearch;

    private DreamBookReader bookReader;
    private DreamBookAdapter mDreamAdapter;

    private List<String> listAllContent;
    private List<String> listAllNumber;

    private List<String> listSearchContent;
    private List<String> listSearchNumber;


    private TextView textDigitFirst1;
    private TextView textDigitFirst2;

    private TextView textDigitSecond1;
    private TextView textDigitSecond2;

    private TextView textDigitThird1;
    private TextView textDigitThird2;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dream_book, container, false);

        initComponent(view);
        return view;
    }

    public void initComponent(View view) {

        listView = (ListView) view.findViewById(R.id.list_dream);
        editSearch = (EditText) view.findViewById(R.id.edit_search);


        textDigitFirst1 = (TextView) view.findViewById(R.id.digit_first_1);
        textDigitFirst2 = (TextView) view.findViewById(R.id.digit_first_2);

        textDigitSecond1 = (TextView) view.findViewById(R.id.digit_second_1);
        textDigitSecond2 = (TextView) view.findViewById(R.id.digit_second_2);

        textDigitThird1 = (TextView) view.findViewById(R.id.digit_third_1);
        textDigitThird2 = (TextView) view.findViewById(R.id.digit_third_2);


        // get all data from asset/dream_book.txt
        // No filter
        bookReader = new DreamBookReader(getActivity());
        bookReader.reader("");

        listAllContent = bookReader.getDreamsListContent();
        listAllNumber = bookReader.getDreamsListNumber();

        listSearchContent = listAllContent;
        listSearchNumber = listAllNumber;


        // Create adapter
        mDreamAdapter = new DreamBookAdapter(
                getActivity(),
                R.layout.row_list_dreams,
                R.id.list_item_dream_content,
                listAllContent
        );


        // Set Adapter
        listView.setAdapter(mDreamAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dreamNumber = bookReader.getDreamsListNumber().get(i);


                // Analyze dreamNumber to put in color circle
                String values[] = dreamNumber.split("-");

                for (int m = 0; m < values.length; m++) {
                    switch (m) {
                        case 0:
                            putToCircle(values[m], textDigitFirst1, textDigitFirst2);
                            break;
                        case 1:
                            putToCircle(values[m], textDigitSecond1, textDigitSecond2);
                            break;
                        case 2:
                            putToCircle(values[m], textDigitThird1, textDigitThird2);
                            break;
                    }
                }

            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                listSearchContent = new ArrayList<String>();
                listSearchNumber = new ArrayList<String>();
            }

            @Override
            public void onTextChanged(CharSequence text, int m, int i2, int i3) {
                // Need to optimize the exactly result
                String filter = editSearch.getText().toString().trim().toLowerCase();
                Log.d(TAG, filter);

                for (int i = 0; i < listAllContent.size(); i++) {

                    if (listAllContent.get(i).toLowerCase().contains(filter)) {
                        listSearchContent.add(listAllContent.get(i));
                        listSearchNumber.add(listAllNumber.get(i));
                    }
                }

                mDreamAdapter.setList(listSearchContent);
                mDreamAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void putToCircle(String value, TextView digit1, TextView digit2) {
        if (value.length() == 1) {
            digit1.setText("0");
            digit2.setText(value);
        } else {
            digit1.setText(String.valueOf(value.charAt(0)));
            digit2.setText(String.valueOf(value.charAt(1)));
        }
    }

}

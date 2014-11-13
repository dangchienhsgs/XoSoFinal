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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;
import fiveplay.dangchienhsgs.com.xosokienthiet.data.DreamBookAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.data.DreamBookReader;

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


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dream_book, container, false);

        listView = (ListView) getView().findViewById(R.id.list_dream);
        editSearch = (EditText) getView().findViewById(R.id.edit_search);

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
                Toast.makeText(getActivity(), dreamNumber, Toast.LENGTH_SHORT).show();
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

        return view;
    }

}

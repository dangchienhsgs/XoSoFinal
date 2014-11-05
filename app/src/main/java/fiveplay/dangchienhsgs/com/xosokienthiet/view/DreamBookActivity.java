package fiveplay.dangchienhsgs.com.xosokienthiet.view;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;
import fiveplay.dangchienhsgs.com.xosokienthiet.data.DreamBookAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.data.DreamBookReader;

public class DreamBookActivity extends Activity {
    private final String TAG="Dream Book Activity";

    private ListView listView;
    private EditText editSearch;

    private DreamBookReader mReader=new DreamBookReader(this);
    private DreamBookAdapter mDreamAdapter;

    private List<String> listAllContent;
    private List<String> listAllNumber;

    private List<String> listSearchContent;
    private List<String> listSearchNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_book);

        listView=(ListView) findViewById(R.id.list_dream);
        editSearch=(EditText) findViewById(R.id.edit_search);

        mReader.reader("");
        listAllContent=mReader.getDreamsListContent();
        listAllNumber=mReader.getDreamsListNumber();

        listSearchContent=listAllContent;
        listSearchNumber=listAllNumber;

        mDreamAdapter=new DreamBookAdapter(
                this,
                R.layout.layout_row_list_dreams,
                R.id.list_item_dream_content,
                listAllContent
        );

        listView.setAdapter(mDreamAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dreamNumber=mReader.getDreamsListNumber().get(i);
                Toast.makeText(getApplicationContext(), dreamNumber, Toast.LENGTH_SHORT).show();
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                listSearchContent=new ArrayList<String>();
                listSearchNumber=new ArrayList<String>();
            }

            @Override
            public void onTextChanged(CharSequence text, int m, int i2, int i3) {
                // Need to optimize the exactly result
                String filter=editSearch.getText().toString().trim().toLowerCase();
                Log.d(TAG, filter);

                for (int i=0; i<listAllContent.size(); i++){

                    if (listAllContent.get(i).toLowerCase().contains(filter)){
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dream_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

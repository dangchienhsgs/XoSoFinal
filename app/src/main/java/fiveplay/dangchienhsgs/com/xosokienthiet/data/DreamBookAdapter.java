package fiveplay.dangchienhsgs.com.xosokienthiet.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;


/**
 * Adapter for Dream Book Activity 's ListView
 */
public class DreamBookAdapter extends ArrayAdapter<String>{
    List<String> list;

    public DreamBookAdapter(Context context, int resource, int textViewResourceId, List<String> list) {
        super(context, resource, textViewResourceId, list);
        setList(list);
    }

    public void setList(List<String> list){
        this.list=list;
    }


    @Override
    public int getCount() {
        return list.size();
    }
}

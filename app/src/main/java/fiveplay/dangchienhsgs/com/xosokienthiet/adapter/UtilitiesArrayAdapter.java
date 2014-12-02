package fiveplay.dangchienhsgs.com.xosokienthiet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;

/**
 * Created by dangchienbn on 13/11/2014.
 */
public class UtilitiesArrayAdapter extends ArrayAdapter<String> {

    public UtilitiesArrayAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //super.getView(position, convertView, parent);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_utilities, parent, false);
        }

        TextView text = (TextView) convertView.findViewById(R.id.text_utility_title);

        text.setText(getItem(position));

        return convertView;
    }
}

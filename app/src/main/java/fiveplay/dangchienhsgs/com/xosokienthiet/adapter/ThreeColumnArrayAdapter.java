package fiveplay.dangchienhsgs.com.xosokienthiet.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;

/**
 * Created by dangchienhsgs on 05/11/2014.
 */
public class ThreeColumnArrayAdapter extends ArrayAdapter<String> {
    private final String TAG = "Prize and Value Array Adapter";

    private List<String> listFirstColumn;
    private List<String> listSecondColumn;
    private List<String> listThirdColumn;

    public ThreeColumnArrayAdapter(Context context, int resource, int textViewResourceId,
                                   List<String> listFirstColumn, List<String> listSecondColumn,
                                   List<String> listThirdColumn) {
        super(context, resource, textViewResourceId, listFirstColumn);
        this.listFirstColumn = listFirstColumn;
        this.listSecondColumn = listSecondColumn;
        this.listThirdColumn = listThirdColumn;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_three_columns, parent, false);
        }

        TextView textFirstColumn = (TextView) convertView.findViewById(R.id.text_first_column);
        TextView textSecondColumn = (TextView) convertView.findViewById(R.id.text_second_column);
        TextView textThirdColumn = (TextView) convertView.findViewById(R.id.text_third_column);


        textFirstColumn.setText(listFirstColumn.get(position));
        textSecondColumn.setText(listSecondColumn.get(position));
        textThirdColumn.setText(listThirdColumn.get(position));

        return convertView;
    }

    @Override
    public int getCount() {
        return listFirstColumn.size();
    }
}

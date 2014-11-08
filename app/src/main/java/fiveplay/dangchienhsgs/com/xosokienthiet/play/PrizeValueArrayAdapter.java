package fiveplay.dangchienhsgs.com.xosokienthiet.play;

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
public class PrizeValueArrayAdapter extends ArrayAdapter<String> {
    private final String TAG="Prize and Value Array Adapter";

    private List<String> listPrizeName;
    private List<String> listPrizeValue;

    public PrizeValueArrayAdapter(Context context, int resource, List<String> listPrizeName, List<String> listPrizeValue) {
        super(context, resource);
        this.listPrizeName=listPrizeName;
        this.listPrizeValue=listPrizeValue;

        Log.d(TAG, listPrizeName.toString()+ listPrizeValue.toString());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.layout_row_list_prize_and_value, parent);
        }

        TextView textPrizeName=(TextView) convertView.findViewById(R.id.text_prize_name);
        TextView textPrizeValue=(TextView) convertView.findViewById(R.id.text_prize_value);

        textPrizeName.setText(listPrizeName.get(position));
        textPrizeValue.setText(listPrizeValue.get(position));

        return convertView;
    }

    public void setListPrizeName(List<String> listPrizeName) {
        this.listPrizeName = listPrizeName;
    }

    public void setListPrizeValue(List<String> listPrizeValue) {
        this.listPrizeValue = listPrizeValue;
    }

    public List<String> getListPrizeName() {
        return listPrizeName;
    }

    public List<String> getListPrizeValue() {
        return listPrizeValue;
    }
}

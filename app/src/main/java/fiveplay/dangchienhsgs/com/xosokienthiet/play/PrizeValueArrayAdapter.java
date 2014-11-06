package fiveplay.dangchienhsgs.com.xosokienthiet.play;

import android.content.Context;
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
    private List<String> listPrizeName;
    private List<String> listPrizeValue;

    public PrizeValueArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=super.getView(position, convertView, parent);
        TextView textPrizeName=(TextView) view.findViewById(R.id.text_prize_name);
        TextView textPrizeValue=(TextView) view.findViewById(R.id.text_prize_value);

        textPrizeName.setText(listPrizeName.get(position));
        textPrizeValue.setText(listPrizeValue.get(position));

        return view;
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

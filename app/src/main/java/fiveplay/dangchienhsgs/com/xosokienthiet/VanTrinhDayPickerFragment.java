package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.prefs.Preferences;

import fiveplay.dangchienhsgs.com.xosokienthiet.utils.PreferencesHandler;

/**
 * Created by dangchienbn on 20/11/2014.
 */
public class VanTrinhDayPickerFragment extends Fragment implements Button.OnClickListener {

    private final String TAG = "Van Trinh Day Picker Fragment";

    private int year = 1994;
    private int month = 11;
    private int day = 19;


    private DatePicker datePicker;
    private Button buttonViewResult;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);

        initComponents(view);

        return view;
    }

    public void initComponents(View view) {

        datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        datePicker.updateDate(year, month, day);

        buttonViewResult = (Button) view.findViewById(R.id.button_view_result);
        buttonViewResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        year = datePicker.getYear();
        month = datePicker.getMonth();
        day = datePicker.getDayOfMonth();


        PreferencesHandler.saveValueToPreferences(Common.KEY_VAN_TRINH_DAY, String.valueOf(day), getActivity());
        PreferencesHandler.saveValueToPreferences(Common.KEY_VAN_TRINH_MONTH, String.valueOf(month), getActivity());
        PreferencesHandler.saveValueToPreferences(Common.KEY_VAN_TRINH_YEAR, String.valueOf(year), getActivity());

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setIndexVanTrinhFragment(0);
        mainActivity.replaceFragment(R.id.fragment_van_trinh_root, new VanTrinhResultFragment());
    }
}

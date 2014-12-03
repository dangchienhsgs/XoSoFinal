package fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.alerterror;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;


public class NetworkErrorDialog extends DialogFragment {

    private String title;
    private String content;

    private OnRetryListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_alert_network, null);
        TextView textView = (TextView) view.findViewById(R.id.text_alert);

        textView.setText(content);

        builder.setView(view);
        builder.setTitle(title);

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onDialogClose();
                getDialog().cancel();
            }
        });

        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onDialogRetry();
                getDialog().cancel();
            }
        });

        return builder.create();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setListener(OnRetryListener mListener) {
        this.mListener = mListener;
    }

    public interface OnRetryListener {
        public void onDialogRetry();

        public void onDialogClose();
    }
}

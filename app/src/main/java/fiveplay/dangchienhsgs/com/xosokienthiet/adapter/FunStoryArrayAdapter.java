package fiveplay.dangchienhsgs.com.xosokienthiet.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;


public class FunStoryArrayAdapter extends ArrayAdapter<String> {

    private List<String> listIntro;
    private List<String> listTitle;


    public FunStoryArrayAdapter(Context context, int resource, List<String> listTitle, List<String> listIntro) {
        super(context, resource, listTitle);

        this.listTitle = listTitle;
        this.listIntro = listIntro;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_fun_story, parent, false);
        }

        //ImageView imageIcon = (ImageView) convertView.findViewById(R.id.image_icon_story);
        TextView textTitle = (TextView) convertView.findViewById(R.id.text_title_story);
        TextView textIntro = (TextView) convertView.findViewById(R.id.text_intro_story);

        textTitle.setText(listTitle.get(position));
        textIntro.setText(listIntro.get(position));


        return convertView;
    }


    private class DownloadInfoStory extends AsyncTask<Void, String, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }
    }
}

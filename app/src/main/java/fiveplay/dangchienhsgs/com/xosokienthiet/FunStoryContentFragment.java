package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;

public class FunStoryContentFragment extends Fragment {
    private static final String TAG = "Fun Story Content Fragment";

    private int storyID;
    private TextView textContent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_fun_story_content, container, false);
        textContent = (TextView) view.findViewById(R.id.text_fun_story_content);

        return view;
    }


    public void updateView(String json) {
        try {

            Log.d(TAG, json);

            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            String content = jsonObject.getString("Content");
            String imageUrl = jsonObject.getString("ImgUrl");

            textContent.setText(Html.fromHtml(content));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setStoryId(int id) {

        storyID = id;

        new UrlDownloadContent(storyID).execute();

    }

    private class UrlDownloadContent extends AsyncTask<Void, String, String> {
        private int id;

        private UrlDownloadContent(int id) {
            this.id = id;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String url = "http://xs.icsoft.vn/NewsJsonServices/NewsService.svc/GetArticlesById/" + id + ",4,livexs,zyz";
            Log.d(TAG, url);
            String content = ServiceUtilities.sendGet(url, null);
            content = content.substring(1, content.length() - 1);

            content = content.replace("\\\\", "\\");
            content = content.replace("\\\"", "\"");

            Log.d(TAG, content);

            return content;
        }


        @Override
        protected void onPostExecute(String result) {
            updateView(result);
        }


    }
}

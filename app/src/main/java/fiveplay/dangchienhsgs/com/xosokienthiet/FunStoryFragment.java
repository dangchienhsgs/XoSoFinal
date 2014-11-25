package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.FunStoryArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;


public class FunStoryFragment extends Fragment {
    private static final String TAG = "Fun Story Fragment";
    private ListView listView;

    private List<Integer> listId;
    private List<String> listTitle;
    private List<String> listIntro;
    private List<String> listImageLinks;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_fun_story, container, false);
        initComponents(view);

        return view;

    }


    public void initComponents(View view) {
        listView = (ListView) view.findViewById(R.id.list_fun_story);

        new DownloadUrlTask(0, 20).execute();
    }


    public void analyzeResult(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);

            Log.d(TAG, jsonArray.length() + "");


            listId = new ArrayList<Integer>();
            listIntro = new ArrayList<String>();
            listTitle = new ArrayList<String>();
            listImageLinks = new ArrayList<String>();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);


                listImageLinks.add(jsonObject.getString("ImgUrl"));
                listId.add(jsonObject.getInt("ArticleId"));
                listTitle.add(jsonObject.getString("Title"));
                listIntro.add(jsonObject.getString("Lead"));
            }

            Log.d(TAG, listId.toString());
            Log.d(TAG, listTitle.toString());
            Log.d(TAG, listIntro.toString());


        } catch (JSONException e) {
            Log.d(TAG, "JSON Error: " + result);
        }
    }

    public void updateView() {
        FunStoryArrayAdapter adapter = new FunStoryArrayAdapter(
                getActivity(),
                R.layout.row_list_fun_story,
                listIntro,
                listTitle
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FunStoryContentFragment funStoryContentFragment = new FunStoryContentFragment();
                funStoryContentFragment.setStoryId(listId.get(i));

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setIndexFunStoryFragment(0);
                mainActivity.replaceFragment(R.id.fragment_fun_story_root, funStoryContentFragment);
            }
        });
    }


    private class DownloadUrlTask extends AsyncTask<Void, String, String> {
        private int indexPage;
        private int numStoryInPage;

        private DownloadUrlTask(int indexPage, int numStoryInPage) {
            this.indexPage = indexPage;
            this.numStoryInPage = numStoryInPage;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String url = "http://xs.icsoft.vn/NewsJsonServices/NewsService.svc/GetArticlesByCate/164," + indexPage + "," + numStoryInPage + ",4,livexs,zyz";

            Log.d(TAG, url);

            String content = ServiceUtilities.sendGet(url, null);
            content = content.substring(1, content.length() - 1);

            Log.d(TAG, content);

            content = content.replace("\\\\", "\\");
            content = content.replace("\\\"", "\"");

            Log.d(TAG, content);

            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            analyzeResult(result);
            updateView();
        }
    }

}

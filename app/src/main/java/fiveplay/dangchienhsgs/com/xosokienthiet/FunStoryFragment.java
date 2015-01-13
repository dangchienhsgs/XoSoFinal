package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.FunStoryArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.alerterror.NetworkErrorDialog;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;


public class FunStoryFragment extends Fragment {
    private static final String TAG = "Fun Story Fragment";
    FunStoryArrayAdapter adapter;
    private ListView listView;
    private List<Integer> listId;
    private List<String> listTitle;
    private List<String> listIntro;
    private List<String> listImageLinks;
    private int indexPage;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_fun_story, container, false);
        initComponents(view);


        listId = new ArrayList<Integer>();
        listIntro = new ArrayList<String>();
        listTitle = new ArrayList<String>();
        listImageLinks = new ArrayList<String>();

        indexPage = 0;

        return view;

    }


    public void initComponents(View view) {
        listView = (ListView) view.findViewById(R.id.list_fun_story);

        new DownloadUrlTask(indexPage, 10).execute();
    }


    public void analyzeResult(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);

            Log.d(TAG, jsonArray.length() + "");

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
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new FunStoryArrayAdapter(
                    getActivity(),
                    R.layout.row_list_fun_story,
                    listTitle,
                    listIntro
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

                    EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(
                            "Fun Story",
                            "Fun Story View",
                            "Fun Story",
                            null).build());
                }
            });

            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {

                }

                @Override
                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                    int current = i + i2;

                    // If at bottom, load more story
                    if (current == i3) {
                        indexPage = indexPage + 1;
                        new DownloadUrlTask(indexPage, 10).execute();
                    }
                }
            });
        }

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

            String content = ServiceUtilities.sendGet(url, null);


            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                result = result.substring(1, result.length() - 1);
                result = result.replace("\\\\", "\\");
                result = result.replace("\\\"", "\"");

                if (result.trim().length() > 5) {
                    analyzeResult(result);
                    updateView();
                }
            } catch (Exception e) {
                NetworkErrorDialog dialog = new NetworkErrorDialog();
                dialog.setTitle("Thông báo");
                dialog.setContent("Lỗi mạng hoặc lỗi server, ấn retry để kết nối lại !");
                dialog.setListener(new NetworkErrorDialog.OnRetryListener() {
                    @Override
                    public void onDialogRetry() {
                        new DownloadUrlTask(indexPage, 10).execute();
                    }

                    @Override
                    public void onDialogClose() {
                        getActivity().onBackPressed();
                    }
                });

                dialog.show(FunStoryFragment.this.getFragmentManager(), "Error Network Dialog");
            }
        }
    }

}

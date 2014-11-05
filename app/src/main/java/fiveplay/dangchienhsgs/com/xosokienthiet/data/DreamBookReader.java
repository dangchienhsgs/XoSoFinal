package fiveplay.dangchienhsgs.com.xosokienthiet.data;

import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DreamBookReader {
    private final String TAG="Dream Book Reader";
    private final String FILE_NAME="dream_book.txt";

    private Context context;

    private List<String> dreamsListContent;
    private List<String> dreamsListNumber;



    public DreamBookReader(Context context) {
        this.context=context;
    }


    /**
     * Fetch all rows which the dreamContent contains the filter String
     * Filter all rows by set filter=""
     * @param filter: filter String
     */
    public List<String> reader(String filter){
        try{
            if (filter==null) {
                filter="";
            }

            InputStream inputStream=context.getAssets().open(FILE_NAME);
            Scanner scanner=new Scanner(inputStream);


            // init array list
            dreamsListContent=new ArrayList<String>();
            dreamsListNumber=new ArrayList<String>();

            while (scanner.hasNextLine()){
                String line=scanner.nextLine();

                // Analyze file
                String[] values=line.trim().split(",");


                // values [0] is the index
                // values [1] is the dream's content
                // values [2] is the dream's suggested number
                if (line.contains(filter.trim())){
                    dreamsListContent.add(values[1]);
                    dreamsListNumber.add(values[2]);

                }
            }

            scanner.close();
            inputStream.close();

            return dreamsListContent;
        } catch (IOException e){
            e.printStackTrace();
            Log.d(TAG, "Can not find file "+FILE_NAME);
            return null;
        }
    }

    public List<String> getDreamsListContent() {
        return dreamsListContent;
    }

    public List<String> getDreamsListNumber() {
        return dreamsListNumber;
    }
}

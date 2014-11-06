package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fiveplay.dangchienhsgs.com.xosokienthiet.view.DreamBookActivity;
import fiveplay.dangchienhsgs.com.xosokienthiet.view.ScheduleRollingActivity;
import fiveplay.dangchienhsgs.com.xosokienthiet.play.TryPlayActivity;


public class MainActivity extends Activity implements Button.OnClickListener{
    private String TAG="Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        Intent intent;

        switch (id){
            case R.id.button_dream_book:
                intent=new Intent(getApplicationContext(), DreamBookActivity.class);
                startActivity(intent);
                break;
            case R.id.button_rolling_schedule:
                intent=new Intent(getApplicationContext(), ScheduleRollingActivity.class);
                startActivity(intent);
                break;
            case R.id.button_try_play:
                intent=new Intent(getApplicationContext(), TryPlayActivity.class);
                startActivity(intent);
                break;
        }
    }
}

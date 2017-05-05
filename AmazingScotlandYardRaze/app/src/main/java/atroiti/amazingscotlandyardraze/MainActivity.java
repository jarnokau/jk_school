package atroiti.amazingscotlandyardraze;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button RobberButton,PoliceButton;
    Button ContinueButton, EndGameButton;
    Boolean newGame;
    public static final String PREF = "gameFile";
    private String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String path = String.valueOf(getFilesDir());

        SharedPreferences sharedPrefs = getSharedPreferences(PREF, MODE_PRIVATE);
        // check is there game role present
        Role = sharedPrefs.getString("ROLE","nothing");
        Log.i("Variable",Role);
        if(Role!="nothing") {
             //sharePreferences found
            Log.i("Status","Old game");
            newGame = false;
        }else{
             //sharePreferences not found
            Log.i("Status","New game");
            newGame = true;
        }
Log.i("loadView","activityMain");
        setContentView(R.layout.activity_main);
        //get handles at activity_main view
        TabLayout TabLay = (TabLayout) findViewById(R.id.tabs);
        ViewPager VPager = (ViewPager) findViewById(R.id.viewpager);
Log.i("handles","both created");
        //create NewGameAdapter Object
        NewGamePageAdapter MyPager = new NewGamePageAdapter(getSupportFragmentManager());
        VPager.setAdapter(MyPager);
        TabLay.setupWithViewPager(VPager);
Log.i("view","newgame");

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
    public boolean fileExistance(String fname){
        //check if file exists
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    public boolean deleteFile(String fname){
        //when ending game remove gamefile
        File dir = getFilesDir();
        File file = new File(dir, fname);
        boolean deleted = file.delete();
        return deleted;
    }

    public boolean createAndWriteFile(String fname, String Role, String OtherPlayer) {
        //File dir = getFilesDir();
        //File file = new File(dir, fname);
        //FileOutputStream stream = new FileOutputStream(fname);
        //String filename = "myfile";
        //String string = "Hello world!";

        //write are you Robber or Police
        String string = Role + "\n\r";
        //write contact information of other player
        string = string + OtherPlayer + "\n\r";

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(fname, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //change this to return was read succesful or not
        return true;
    }

    public Boolean readFile(String fname){

        //change so it will return readed information, also change funktion type from Boolean to correct one
        return true;
    };


}

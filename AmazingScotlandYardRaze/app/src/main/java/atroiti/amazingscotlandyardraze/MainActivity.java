package atroiti.amazingscotlandyardraze;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button RobberButton,PoliceButton;
    Button ContinueButton, EndGameButton;
    Boolean newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String path = String.valueOf(getFilesDir());
        String filename = "gameFile.cfg";
        Boolean fileExists;
        // check is there game file present
        fileExists = fileExistance(filename);
        if(fileExists) {
            newGame = false;
            // if file exist, read information from it(is player robber/police etc) then later on based on that open correct view

            //read rows from given file
            //first row contains role: Robber or Police
            //second row contains contact information of another player

            //inside activity_main open content_main2 = continue or not
            //this happens only when starting application

            //if continue open role related view
            //if does not continue, delete game file and open new game view
        }else{
            newGame = true;
        }
            //if file does not exists, open "new game view"

            setContentView(R.layout.activity_main);
            RobberButton = (Button)(findViewById(R.id.Location1_button));
            RobberButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {

                                                }
                                            });
            // inside activity_main open content_main = new game view
            //




        // game file will be removed when game ends (using "end game" button)

        //setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

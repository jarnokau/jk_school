package atroiti.amazingscotlandyardraze;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String path = String.valueOf(getFilesDir());
        String filename = "gameFile.cfg";
        Boolean fileExists;
        fileExists = fileExistance(filename);
        if(fileExists) {
            // if file exists
            //read file information
            //first row contains role: Robber or Police
            //second row contains contact information of another player
            setContentView(R.layout.activity_main);
            //inside activity_main open content_main2 = continue or not
            //this happens only when starting application

            //if continue open role related view
            //if does not continue, delete game file and open new game view
        }else{
            //if file does not exists, open "new game view"
            setContentView(R.layout.activity_main);
            // inside activity_main open content_main = new game view
            //
        }

        // check is there game file present
        // if file exist, read information from it(is player robber/police etc) then later on based on that open correct view
        // if file does not exist, first screen will be "new game"
        // game file will be removed when game ends

        setContentView(R.layout.activity_main);
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

    public boolean createAndWriteFile(String fname, String Role, String OtherPlayer) throws IOException {
        File dir = getFilesDir();
        File file = new File(dir, fname);
        FileOutputStream stream = new FileOutputStream(fname);
        try {
            //write are you Robber or Police
            stream.write(Role.getBytes());
            //write contact information of other player
            stream.write(OtherPlayer.getBytes());
        } finally {
            stream.close();
        }
        //change this to return was read succesful or not
        return true;
    }

    public Boolean readFile(String fname){
        //read rows from given file
        //first row contains role
        // second row contains contact infromation to other player
        //change so it will return readed information, also change funktion type from Boolean to correct one
        return true;
    };


}

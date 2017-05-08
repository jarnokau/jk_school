package atroiti.amazingscotlandyardraze;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.lang.String;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity {
    private LocationManager locMana;
    private LocationListener locLis;
    //static protected Location startLoc = null;
    public Criteria criteria;
    public String bestProvider;
    public double latitude;
    public double longitude;
    Button RobberButton, PoliceButton;
    Button ContinueButton, EndGameButton;
    NewGamePageAdapter MyPager;
    Boolean newGame;
    public static final String PREF = "gameFile";
    private String Role;
    static final int NUM_ITEMS = 2;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int MINIMUM_TIME_BETWEEN_UPDATES = 500;
    static final int MINIMUM_DISTANCE_CHANGE_FOR_UPDATES =0 ;

    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String path = String.valueOf(getFilesDir());


        SharedPreferences sharedPrefs = getSharedPreferences(PREF, MODE_PRIVATE);

        //sendEmail();
        // check is there game role present
        Role = getRole(this);
        //Role = sharedPrefs.getString("ROLE","nothing");
        Log.i("Variable", Role);
        if (Role != "nothing") {
            //sharePreferences found
            Log.i("Status", "Old game");
            newGame = false;
        } else {
            //sharePreferences not found
            Log.i("Status", "New game");
            newGame = true;
        }

        Log.i("loadView", "activityMain");
        setContentView(R.layout.activity_main);
        //get handles at activity_main view
        TabLayout TabLay = (TabLayout) findViewById(R.id.tabs);
        ViewPager VPager = (ViewPager) findViewById(R.id.viewpager);
        Log.i("handles", "both created");
        //create NewGameAdapter Object
        MyPager = new NewGamePageAdapter(getSupportFragmentManager());
        VPager.setAdapter(MyPager);
        TabLay.setupWithViewPager(VPager);
        Log.i("view", "newgame");

    }

    public class NewGamePageAdapter extends FragmentPagerAdapter {
        public NewGamePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
			/*
			 * IMPORTANT: This is the point. We create a RootFragment acting as
			 * a container for other fragments
			 */
            if (position == 0)
                return new RootFragment();
            else
                return new StaticFragment();
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    public static class RootFragment extends Fragment {

        private static final String TAG = "RootFragment";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
            View view = inflater.inflate(R.layout.root_fragment, container, false);
//replace root_frame with newGameView
            FragmentTransaction transaction = getFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.root_frame, new NewGameView());
            transaction.commit();

            return view;
        }

    }

    public static class StaticFragment extends Fragment {

        private static final String TAG = "StaticFragment";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater
                    .inflate(R.layout.static_fragment, container, false);
            return view;
        }
    }

    public static void setRole(String value, Context context) {
        //stores selected role
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ROLE", value);
        editor.commit();
    }

    public static void setOtherPlayer(String value, Context context) {
        //stores otherplayers contact info
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("OtherPlayer", value);
        editor.commit();
    }

    public static boolean DeleteRole(Context context) {
        //deletes chosen role
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        prefs.edit().remove("ROLE");
        //prefs.edit().commit();
        prefs.edit().apply();
        return true;
    }

    public static boolean DeleteOtherPlayer(Context context) {
        //deletes otherplayer contact info
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        prefs.edit().remove("OtherPlayer");
        //prefs.edit().commit();
        prefs.edit().apply();
        return true;
    }

    public static String getRole(Context context) {
        //returns stored value of Role, if does not exist, returns "nothing" as default
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return prefs.getString("ROLE", "nothing");
    }

    public static String getOtherPlayer(Context context) {
        //returns stored contact info  of otherplayer, if does not exist, returns "nothing" as default
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return prefs.getString("OtherPlayer", "nothing");
    }

    protected void sendEmail(String email, String body, Context context) {
        //Works
        Log.i("Send email", " starting ");
        String[] TO = {email};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        Log.i("Send email", " starting activity");
        try {
            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Email", "Sent successfully");
        } catch (android.content.ActivityNotFoundException ex) {
            Log.i("email", "no Client installed");
        } catch (Exception ex) {
            Log.e("error", "err", ex);
        }
        ;
    }

    public void sendMMS(Context context) {
        Log.i("here", "in MMS function");
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        Log.i("here", "after sentIntent");
        sendIntent.setClassName("com.android.mms", "com.android.mms.ui.ComposeMessageActivity");
        Log.i("here", "after setClassName");
        sendIntent.putExtra("address", "0405707346");
        sendIntent.putExtra("sms_body", "test message");
        //sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/image_4.png"));
        //sendIntent.setType("image/png");
        Log.i("here", "next will startActivity");
        context.startActivity(sendIntent);
    }

    protected String GPS(Context context) {
        Log.i("GPS", "Getting GPS location");
         LocationManager mLocationManager;
        Log.i("GPS", "define Provider");
         String PROVİDER = LocationManager.GPS_PROVIDER;
        Log.i("GPS", "set mLocation to null");
        Location mLocation = null;
        Log.i("GPS", "call LOCATION Service");

        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
             Log.i("GPS", "Ask for last location");
        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now

            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
            // permission has been granted, continue as usual
            //mLocation = mLocationManager.getLastKnownLocation(PROVİDER);
            locLis = new LocationListener(){

                @Override
                public void onLocationChanged(Location location){

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) { }

                @Override
                public void onProviderEnabled(String provider) { }

                @Override
                public void onProviderDisabled(String provider) { }
            };


        }
        //write location to logcat in stringformat to check if it is null

        Log.i("GPS", String.valueOf(latitude));
        Log.i("GPS", String.valueOf(longitude));

        if (latitude == 0.0) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,locLis);
            Log.d("GPS", "GPS Enabled");
            if (mLocationManager != null) {
                mLocation = mLocationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (mLocation != null) {
                    latitude = mLocation.getLatitude();
                    longitude = mLocation.getLongitude();
                }

            }
        }

        Log.i("GPS", String.valueOf(latitude));
        Log.i("GPS", String.valueOf(longitude));
        return mLocation.toString();
        }

public boolean activateCamera(Context context) {
    Log.i("here","in ActivateCamera");

    //private void dispatchTakePictureIntent() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    Log.i("here","intent created");
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        Log.i("here","takepicture not null so start activity");
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        //context.startActivity(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
    return true;
    }

}

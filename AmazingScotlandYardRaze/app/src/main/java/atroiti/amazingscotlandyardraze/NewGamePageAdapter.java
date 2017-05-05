package atroiti.amazingscotlandyardraze;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by cpt2jk2 on 5.5.2017.
 */
/* luodaan mypageAdapter luokka */
public class NewGamePageAdapter extends FragmentPagerAdapter{

        @Override
        public int getCount() {
            //how many menu items
            return 1;
        }
        public NewGamePageAdapter(FragmentManager fm) {
            super(fm);
        }
        public CharSequence getPageTitle(int position){
            //Pager selection texts
            return "Choose you role";
        }

        @Override
        public Fragment getItem(int position) {
            //this java class is called
            return new NewGameView();

        }
}



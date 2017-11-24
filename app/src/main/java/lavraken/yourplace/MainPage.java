package lavraken.yourplace;

import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPage extends AppCompatActivity {

        private DrawerLayout mDrawer;
        private Toolbar toolbar;
        private NavigationView nvDrawer;
        private Class fragmentC;
        private Fragment fragment1;

        private NotificationCompat.Builder mBuilder;
        private long warmUpInit;
        private long warmUpReady;
        private boolean initLock = true;

        private ActionBarDrawerToggle drawerToggle;

        DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mBookingRef = mRootReference.child("User");

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            warmUpInit = System.nanoTime();
            warmUpReady = warmUpInit + 2000000000;

            setContentView(R.layout.activity_main);

            mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Your Place ♡")
                    .setContentText("You have new appointent :)");


            try {
                fragmentC = FragmentMain.class;
                fragment1 = (Fragment) fragmentC.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }


            if(savedInstanceState == null) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.flContent, fragment1);
                transaction.commit();

            }

            // Set a Toolbar to replace the ActionBar.
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // Find our drawer view
            nvDrawer = (NavigationView) findViewById(R.id.nvView);

            // Setup drawer view
            setupDrawerContent(nvDrawer);

            mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerToggle = setupDrawerToggle();

            // Tie DrawerLayout events to the ActionBarToggle
            mDrawer.addDrawerListener(drawerToggle);

            // Setup drawer view
            setupDrawerContent(nvDrawer);

            View headerLayout = nvDrawer.getHeaderView(0);
        }

    @Override
    protected void onStart() {
        super.onStart();

        mBookingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //dataSnapshot.getValue(String.class);

                if (System.nanoTime() > warmUpReady){

                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(001, mBuilder.build());

                    //dataSnapshot.getValue(String.class);
                    //Log.v("SOOOOW data", "firebase: "+ dataSnapshot.getValue().toString());

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
        protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            // Sync the toggle state after onRestoreInstanceState has occurred.
            drawerToggle.syncState();
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            // Pass any configuration change to the drawer toggles
            drawerToggle.onConfigurationChanged(newConfig);
        }

        private ActionBarDrawerToggle setupDrawerToggle() {
            // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
            // and will not render the hamburger icon without it.
            return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
        }

        private void setupDrawerContent(NavigationView navigationView) {
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            selectDrawerItem(menuItem);
                            return true;
                        }
                    });
        }

        public void selectDrawerItem(MenuItem menuItem) {
            // Create a new fragment and specify the fragment to show based on nav item clicked
            Fragment fragment = null;
            Class fragmentClass;
            switch(menuItem.getItemId()) {
                case R.id.nav_book:
                    fragmentClass = FragmentBook.class;
                    break;
                case R.id.nav_contact:
                    fragmentClass = FragmentContact.class;
                    break;
                case R.id.nav_about:
                    fragmentClass = FragmentAbout.class;
                    break;
                case R.id.nav_staff:
                    fragmentClass = FragmentLogIn.class;
                    break;
                case R.id.nav_appointments:
                    fragmentClass = FragmentAppointments.class;
                    break;
                default:
                    fragmentClass = FragmentBook.class;
            }

            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Insert the fragment by replacing any existing fragment
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            transaction.replace(R.id.flContent, fragment).commit();
            transaction.addToBackStack(null);

            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);
            // Set action bar title
            setTitle(menuItem.getTitle());
            // Close the navigation drawer
            mDrawer.closeDrawers();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (drawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
}
        /*implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_book) {
            // Handle the camera action
        } else if (id == R.id.nav_price) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_staff) {
            Intent i = new Intent(MainPage.this, LoginActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}*/

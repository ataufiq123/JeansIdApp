package makanbu.com.makanbu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import makanbu.com.makanbu.R;
import makanbu.com.makanbu.fragmentHome.PagerAdapterHome;


public class Home extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    // [START declare_auth]
    private FirebaseAuth mAuth;

    TabLayout tabLayout;
    ViewPager viewPager;
    private String current_user_id;

    FirebaseFirestore firebaseFirestore;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        createTabLayout();
        createViewPager();

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void createTabLayout() {
        // Create an instance of the tab layout from the view.
        tabLayout = findViewById(R.id.tab_layout);
        mAuth = FirebaseAuth.getInstance();
        // Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText("Berkuah"));
        tabLayout.addTab(tabLayout.newTab().setText("Gorengan"));
        tabLayout.addTab(tabLayout.newTab().setText("Cemilan"));
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.getTabAt(0).setIcon(R.drawable.kuah);
        tabLayout.getTabAt(1).setIcon(R.drawable.goreng);
        tabLayout.getTabAt(2).setIcon(R.drawable.cemil);
    }

    public void createViewPager() {
        viewPager = findViewById(R.id.pager);
        final PagerAdapterHome adapter = new PagerAdapterHome(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setAdapter(adapter);
        viewPager.setAdapter(adapter);

        // Setting a listener for clicks.
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            sendToLogin();
        }

    }

    private void sendToLogin() {
        Intent intent = new Intent(Home.this, LandingActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(this, "Ini pencarian", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.notif:
                signOut();
                Toast.makeText(this, "Ini notif", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signOut() {
        mAuth.signOut();
        Intent i = new Intent(Home.this, LandingActivity.class);
        startActivity(i);
    }

}

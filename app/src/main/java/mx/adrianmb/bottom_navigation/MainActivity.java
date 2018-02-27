package mx.adrianmb.bottom_navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.adrianmb.bottom_navigation.sections.MockFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.navigation) BottomNavigationView navigation;

    private List<MockFragment> fragments = new ArrayList<>();
    private int currentPage;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar();

        navigation.setOnNavigationItemSelectedListener(this);

        fragments.add(MockFragment.newInstance("Home"));
        fragments.add(MockFragment.newInstance("Section 1"));
        fragments.add(MockFragment.newInstance("Section 2"));
        fragments.add(MockFragment.newInstance("Settings"));

        changeFragment(fragments.get(0), R.anim.fadein, R.anim.fadeout);
    }

    public void changeFragment(Fragment fragment, int in, int out) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(in, out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commit();

        try {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            Log.e("STACK", e.getMessage());
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_alerts:
                Toast.makeText(getApplicationContext(), "Not implemented yet",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_menu:
                if (currentPage == 0) break;
                changeFragment(fragments.get(0), R.anim.fadein, R.anim.fadeout);
                currentPage = 0;
                return true;
            case R.id.navigation_appointments:
                if (currentPage == 1) break;
                changeFragment(fragments.get(1), R.anim.fadein, R.anim.fadeout);
                currentPage = 1;
                return true;
            case R.id.navigation_shop:
                if (currentPage == 2) break;
                changeFragment(fragments.get(2), R.anim.fadein, R.anim.fadeout);
                currentPage = 2;
                return true;
            case R.id.navigation_more:
                if (currentPage == 3) break;
                changeFragment(fragments.get(3), R.anim.fadein, R.anim.fadeout);
                currentPage = 3;
                return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();

        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
            if (currentPage == 4) {
                currentPage = 0;
            }
        } else {

            if (doubleBackToExitPressedOnce) {
                finish();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.press_back_exit), Toast.LENGTH_SHORT).show();

            new android.os.Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);

            return;
        }
    }
}

package vivenkko.trailquest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import vivenkko.trailquest.fragments.AccountFragment;
import vivenkko.trailquest.fragments.EventFragment;
import vivenkko.trailquest.fragments.OnListFragmentInteractionListener;
import vivenkko.trailquest.fragments.TrailFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnListFragmentInteractionListener {
    ImageView avatar;
    TextView displayName, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        // Cómo setear los campos de texto e imagen que se encuentran
        // en nav_header_main.xml
        avatar = navigationView.getHeaderView(0).findViewById(R.id.navAvatar);
        displayName = navigationView.getHeaderView(0).findViewById(R.id.navDisplayName);
        email = navigationView.getHeaderView(0).findViewById(R.id.navEmail);

        // Por defecto cargamos TrailFragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, new TrailFragment())
                .commit();

        Bundle extras = getIntent().getExtras();
        String token = extras.getString("token");
        String displayNameBundle = extras.getString("displayName");
        String emailBundle = extras.getString("email");
        String img = extras.getString("avatar");


        Picasso.with(this).load(img).into(avatar);
        displayName.setText(displayNameBundle);
        email.setText(emailBundle);


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
        getMenuInflater().inflate(R.menu.main, menu);
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

        Fragment f = null;

        if (id == R.id.nav_trails) {
            f = TrailFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, f).commit();
        } else if (id == R.id.nav_my_trails) {
            f = TrailFragment.newInstance(2);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, f).commit();
        } else if (id == R.id.nav_favorites) {
            f = TrailFragment.newInstance(2);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, f).commit();
        } else if (id == R.id.nav_ranking) {

        } else if (id == R.id.nav_events) {
            f = TrailFragment.newInstance(4);
        } else if (id == R.id.nav_following) {
            f = TrailFragment.newInstance(5);
        } else if (id == R.id.nav_account) {
            f = TrailFragment.newInstance(6);
        }

        if(f!=null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, f)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

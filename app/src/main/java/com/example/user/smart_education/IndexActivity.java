package com.example.user.smart_education;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class IndexActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] titreNavigations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        mTitle = mDrawerTitle = getTitle();
        titreNavigations = getResources().getStringArray(R.array.navigations_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, titreNavigations));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        View header = getLayoutInflater().inflate(R.layout.headerview, null);

        mDrawerList.addHeaderView(header, null, false);
        mDrawerList.setDividerHeight(10);

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /* Remplacer l'image Up caret par un bouton Toggle. */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(1);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putInt(IndexFragment.ARG_NAVIGATION_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(titreNavigations[position-1]);
        if (position == 1) {
            setTitle("Smart Education");
        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    public static class IndexFragment extends Fragment {
        public static final String ARG_NAVIGATION_NUMBER = "navigation_number";

        public IndexFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_index, container, false);
            int i = getArguments().getInt(ARG_NAVIGATION_NUMBER);
            String navigation = getResources().getStringArray(R.array.navigations_array)[i-1];

            switch(navigation) {
                case "Accueil":
                    rootView = inflater.inflate(R.layout.fragment_index, container, false);
                    return rootView;

                case "Assuidité scolaire":
                    rootView = inflater.inflate(R.layout.fragment_assuidite, container, false);
                    return rootView;

                case "Affichage des notes":
                    rootView = inflater.inflate(R.layout.fragment_affichage, container, false);
                    return rootView;

                case "Travail scolaire":
                    rootView = inflater.inflate(R.layout.fragment_travail, container, false);
                    return rootView;

                case "Fiche de suivi":
                    rootView = inflater.inflate(R.layout.fragment_fiche, container, false);
                    return rootView;

                case "Actualité":
                    rootView = inflater.inflate(R.layout.fragment_actualite, container, false);
                    return rootView;



            }

            return rootView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void Assuidite(View view) {
        selectItem(2);
    }

    public void Affichage(View view) {
        selectItem(3);
    }

    public void Travail(View view) {
        selectItem(4);
    }

    public void Fiche(View view) {
        selectItem(5);
    }

    public void Atualite(View view) {
        selectItem(6);
    }

}

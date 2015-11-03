package edu.mbhs.silverquill;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ListView;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    final int ISSUES = 1;
    final int ABOUT = 2;

    private static ListView issuesListView;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if(position == ISSUES) {
            // update the main content by replacing fragments
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, IssuesFragment.newInstance(position + 1))
                    .commit();
        }
        else if(position == ABOUT){
            Intent intent = new Intent(this, PDFViewer.class);
            intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, "/storage/emulated/0/Download/77_Trap.pdf");
            startActivity(intent);
        }
        else {
            // update the main content by replacing fragments
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                    .commit();
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void openPDF(View v){
        Intent intent = new Intent(this, PDFViewer.class);
        intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, "/storage/emulated/0/Download/77_Trap.pdf");
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class IssuesFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private int mCurrentSelectedPosition;

        private String[] issueNames;
        private String[] issueDates;
        private Integer[] thumbnails;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static IssuesFragment newInstance(int sectionNumber) {
            IssuesFragment fragment = new IssuesFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public IssuesFragment() {
        }

        private void readFromDatabase(){

            //For now issue names, dates and thumbnails are hardcoded but eventually they'll be read from the database

            issueNames = new String[]{
                    getString(R.string.issue_1),
                    getString(R.string.issue_2),
                    getString(R.string.issue_3),
                    getString(R.string.issue_4),
                    getString(R.string.issue_5),
                    getString(R.string.issue_6),
                    getString(R.string.issue_7),
                    getString(R.string.issue_8),
                    getString(R.string.issue_9),
                    getString(R.string.issue_10),
                    getString(R.string.issue_11),
                    getString(R.string.issue_12),
            };

            issueDates = new String[]{
                    getString(R.string.issue_1_date),
                    getString(R.string.issue_2_date),
                    getString(R.string.issue_3_date),
                    getString(R.string.issue_4_date),
                    getString(R.string.issue_5_date),
                    getString(R.string.issue_6_date),
                    getString(R.string.issue_7_date),
                    getString(R.string.issue_8_date),
                    getString(R.string.issue_9_date),
                    getString(R.string.issue_10_date),
                    getString(R.string.issue_11_date),
                    getString(R.string.issue_12_date),
            };

            thumbnails = new Integer[]{
                    getResources().getIdentifier("rhapsody", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("terraincognita", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("abstraction", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("inktrack", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("aurora", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("sample_thumbnail", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("sample_thumbnail", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("sample_thumbnail", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("sample_thumbnail", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("sample_thumbnail", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("sample_thumbnail", "drawable", getActivity().getPackageName()),
                    getResources().getIdentifier("sample_thumbnail", "drawable", getActivity().getPackageName())
            };
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            readFromDatabase();

            issuesListView = (ListView) inflater.inflate(R.layout.fragment_issues, container, false);

            issuesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectItem(position);
                }
            });

            issuesListView.setAdapter(new IssueList(((ActionBarActivity) getActivity()), issueNames, issueDates, thumbnails));

            issuesListView.setItemChecked(mCurrentSelectedPosition, true);

            return issuesListView;
        }

        private void selectItem(int position) {
            mCurrentSelectedPosition = position;
            if (issuesListView != null) {
                issuesListView.setItemChecked(position, true);
            }
            /**
            if (mDrawerLayout != null) {
                mDrawerLayout.closeDrawer(mFragmentContainerView);
            }
            if (mCallbacks != null) {
                mCallbacks.onNavigationDrawerItemSelected(position);
            }
             */

            if(position == 0){
                Intent intent = new Intent(getActivity(), PDFViewer.class);
                intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, "/storage/emulated/0/Download/77_Trap.pdf");
                startActivity(intent);
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}

package sports_recorder.sportsrecorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener, ListView.OnItemClickListener {
    private GameManager gm = GameManager.getInstance();



    private Button runScoredButton, singleButton, doubleButton, tripleButton, homeRunButton, strikeoutButton, walkButton, RBIButton, stealButton, fieldOutButton,scoreButton1, scoreButton2, mButton;

    public static int eventType = R.string.event_type_null;


    public int scoreA, scoreB, singles, doubles, triples, home_runs, strikeouts, walks, field_outs, steals, RBIs, hits, at_bats, runsScored;
    public String gameDateStr;
    private String saved_player_name = "";
    public EditText mEdit;
    TextView mText;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;

    private String items[] = new String[] { "Data Entry","Summary"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Disable original title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //Create new Action Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        getActionBar().setElevation(10);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mDrawerLayout.openDrawer(mDrawerListView);
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.drawer_list_view);
        mDrawerListView.setOnItemClickListener(this);


        ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.nav_drawer_list_item, items);
        mDrawerListView.setAdapter(listAdapter);

        // Set button listeners:
        mButton = (Button)findViewById(R.id.button1);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEdit   = (EditText)findViewById(R.id.editText1);

                saved_player_name=(mEdit.getText().toString());
            }
        });
        singleButton = (Button) findViewById(R.id.singleButton);
        singleButton.setOnClickListener(this);

        doubleButton = (Button) findViewById(R.id.doubleButton);
        doubleButton.setOnClickListener(this);

        tripleButton = (Button) findViewById(R.id.tripleButton);
        tripleButton.setOnClickListener(this);

        homeRunButton = (Button) findViewById(R.id.homeRunButton);
        homeRunButton.setOnClickListener(this);

        strikeoutButton = (Button) findViewById(R.id.strikeoutButton);
        strikeoutButton.setOnClickListener(this);

        walkButton = (Button) findViewById(R.id.walkButton);
        walkButton.setOnClickListener(this);

        fieldOutButton = (Button) findViewById(R.id.fieldOutButton);
        fieldOutButton.setOnClickListener(this);

        stealButton = (Button) findViewById(R.id.stealsButton);
        stealButton.setOnClickListener(this);

        RBIButton = (Button) findViewById(R.id.runsBattedInButton);
        RBIButton.setOnClickListener(this);

        runScoredButton = (Button) findViewById(R.id.runScoredButton);
        runScoredButton.setOnClickListener(this);

        scoreButton1 = (Button) findViewById(R.id.score1);
        scoreButton1.setOnClickListener(this);

        scoreButton2 = (Button) findViewById(R.id.score2);
        scoreButton2.setOnClickListener(this);




        sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);

        editor = sharedPref.edit();

        int defaultValue = 0;





        if (savedInstanceState != null) {
            // Restore value of members from saved state

            this.scoreA = savedInstanceState.getInt(getString(R.string.saved_scoreA), 0);
            this.scoreB = savedInstanceState.getInt(getString(R.string.saved_scoreB), 0);

            this.singles = savedInstanceState.getInt(getString(R.string.saved_singles), 0);

            this.gameDateStr = savedInstanceState.getString(getString(R.string.saved_game_date), "");
            this.doubles = savedInstanceState.getInt(getString(R.string.saved_doubles), 0);
            this.triples = savedInstanceState.getInt(getString(R.string.saved_triples), 0);
            this.home_runs = savedInstanceState.getInt(getString(R.string.saved_home_runs), 0);
            this.strikeouts = savedInstanceState.getInt(getString(R.string.saved_strikeouts), 0);
            this.walks = savedInstanceState.getInt(getString(R.string.saved_walks), 0);
            this.field_outs = savedInstanceState.getInt(getString(R.string.saved_field_outs), 0);
            this.hits = savedInstanceState.getInt(getString(R.string.saved_hits), 0);
            this.at_bats = savedInstanceState.getInt(getString(R.string.saved_at_bats), 0);
            this.RBIs = savedInstanceState.getInt(getString(R.string.saved_RBIs), 0);
            this.steals = savedInstanceState.getInt(getString(R.string.saved_steals), 0);
            this.runsScored = savedInstanceState.getInt(getString(R.string.saved_runs_scored), 0);
            this.saved_player_name = savedInstanceState.getString(getString(R.string.saved_player_name), "");


        } else {


            scoreA = 0;
            scoreB = 0;
            singles=0;
            doubles=0;
            triples=0;
            home_runs=0;
            strikeouts=0;
            walks=0;
            field_outs=0;
            steals=0;
            RBIs=0;
            hits=0;
            at_bats=0;

            runsScored=0;



            Date today = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            gameDateStr = formatter.format(today);
        }

//        System.out.println("Date: " + gameDateStr);

        // Scores:

            scoreButton1.setText("" + scoreA);
            scoreButton2.setText("" + scoreB);



        //Timer

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {



        super.onDestroy();
    }

    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.singleButton:
                Toast.makeText(this, getString(R.string.event_type_single), Toast.LENGTH_SHORT).show();
                eventType = R.string.event_type_single;
                singles++;
                this.hits++;
                this.at_bats++;

                singleButton.setText("Singles " + singles);


                break;
            case R.id.doubleButton:
                Toast.makeText(this, getString(R.string.event_type_double), Toast.LENGTH_SHORT).show();
                eventType = R.string.event_type_double;
                doubles++;
                this.hits++;
                this.at_bats++;
                doubleButton.setText("Doubles " + doubles);

                break;
            case R.id.tripleButton:
                Toast.makeText(this, getString(R.string.event_type_triple), Toast.LENGTH_SHORT).show();
                eventType = R.string.event_type_triple;
                triples++;
                this.hits++;
                this.at_bats++;
                tripleButton.setText("Triples " + triples);
//                field.setColor(Color.LTGRAY);
                break;
            case R.id.homeRunButton:
                Toast.makeText(this, getString(R.string.event_type_home_run), Toast.LENGTH_SHORT).show();
                eventType = R.string.event_type_home_run;
                this.home_runs++;
                this.hits++;
                this.at_bats++;
                homeRunButton.setText("Home Runs " + home_runs);
//                field.setColor(Color.RED);
                break;
            case R.id.strikeoutButton:
                Toast.makeText(this, "Strikeout", Toast.LENGTH_SHORT).show();
                strikeouts++;
                strikeoutButton.setText("Strike outs " + strikeouts);

                this.at_bats++;

                break;
            case R.id.runsBattedInButton:
                Toast.makeText(this, "RBI", Toast.LENGTH_SHORT).show();
                eventType = R.string.event_type_RBI;
                RBIs++;
                RBIButton.setText("RBIs " + RBIs);

                break;
            case R.id.runScoredButton:
                Toast.makeText(this, "Made it home", Toast.LENGTH_SHORT).show();
                eventType = R.string.event_type_scored_run;
                runsScored++;
                runScoredButton.setText("Runs Scored " + runsScored);

                break;

            case R.id.stealsButton:
                Toast.makeText(this, "Steal", Toast.LENGTH_SHORT).show();
                eventType = R.string.event_type_steal;
                steals++;
                stealButton.setText("Steals " + steals);

                break;

            case R.id.walkButton:
                Toast.makeText(this, "Walk", Toast.LENGTH_SHORT).show();
                eventType = R.string.event_type_walk;
                walks++;
                at_bats++;
                walkButton.setText("Walk " + walks);
                break;

            case R.id.fieldOutButton:
                Toast.makeText(this, "Field Out", Toast.LENGTH_SHORT).show();
                eventType = R.string.event_type_field_out;
                field_outs++;
                at_bats++;
                fieldOutButton.setText("Field Outs " + field_outs);
                break;
            case R.id.score1:
                scoreA++;
                scoreButton1.setText("" + scoreA);
                break;
            case R.id.score2:
                scoreB++;
                scoreButton2.setText("" + scoreB);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();    // Call the superclass method first.

        //timerHandler.removeCallbacks((timerRunnable));
        //Button timerButton = (Button)findViewById(R.id.timerButton);
        //timerButton.setText("start");

        editor.commit();    // Save data for the app:
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current game state
        saveGameState(savedInstanceState);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    // Save the game state to a bundle for onSaveInstanceState and for summary view
    public void saveGameState(Bundle savedInstanceState) {



        savedInstanceState.putInt(getString(R.string.saved_scoreA), scoreA);
        savedInstanceState.putInt(getString(R.string.saved_scoreB), scoreB);

        savedInstanceState.putString(getString(R.string.saved_game_date), gameDateStr);
        savedInstanceState.putString(getString(R.string.saved_player_name), saved_player_name);
        savedInstanceState.putInt(getString(R.string.saved_singles), singles);

        savedInstanceState.putInt(getString(R.string.saved_doubles), doubles);

        savedInstanceState.putInt(getString(R.string.saved_triples), triples);

        savedInstanceState.putInt(getString(R.string.saved_home_runs), home_runs);
        savedInstanceState.putInt(getString(R.string.saved_RBIs), RBIs);
        savedInstanceState.putInt(getString(R.string.saved_steals), steals);
        savedInstanceState.putInt(getString(R.string.saved_walks), walks);
        savedInstanceState.putInt(getString(R.string.saved_strikeouts), strikeouts);
        savedInstanceState.putInt(getString(R.string.saved_field_outs), field_outs);
        savedInstanceState.putInt(getString(R.string.saved_runs_scored), runsScored);
        savedInstanceState.putInt(getString(R.string.saved_hits), hits);
        savedInstanceState.putInt(getString(R.string.saved_at_bats), at_bats);


        // Update the game at position 0 to this game:
        gm.setGame(0, savedInstanceState, getApplicationContext());
    }


    public static int getEventType() {
        return eventType;
    }

    public static void resetEventType() {
        eventType = R.string.event_type_null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView lv = (ListView) parent;

//        Toast.makeText(this, items[position], Toast.LENGTH_SHORT).show();

        if (items[position].equals("Summary")) {

            Intent intent = new Intent(this, SummaryDetailActivity.class);
            intent.putExtra("POSITION", 0); // Game position, not drawer menu position
//            Bundle extras = intent.getExtras();
//            saveGameState(extras); // Pass game info bundle at start. Then need a way need to pass it to the fragment
            Bundle bundle = new Bundle(); // Try just setting game 0:
            saveGameState(bundle);
            gm.setGame(0, bundle, getApplicationContext()); // Sets game 0
            startActivity(intent);
        }

        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }


    }




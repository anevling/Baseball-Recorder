        package sports_recorder.sportsrecorder;


        import android.app.Activity;
        import android.app.Fragment;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import android.widget.Toast;

public class SummaryDetailFragment extends Fragment {
    private GameManager gm = GameManager.getInstance();
    private Game game = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary_detail, container, false);
    }

    // Tell the fragment which game's stats to display
    public void loadPosition(int position) {
        Game g = gm.get(position);
        game = g;

        Activity activity = getActivity();
        TextView tv = (TextView) activity.findViewById(R.id.editDateText);
        tv.setText(g.gameDateStr);

        tv = (TextView) activity.findViewById(R.id.editScoreText);
        String s = g.scoreA + " to " + g.scoreB;
        System.out.println(g.singles);
        tv.setText(s);





        tv = (TextView) activity.findViewById(R.id.editfieldOutsText);
        s = "" + g.fieldOuts;
        tv.setText(s);


        tv = (TextView) activity.findViewById(R.id.editSinglesText);
        s = g.singles+"";
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editDoublesText);
        s = "" + g.doubles;
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editTriplesText);
        s = "" + g.triples;
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editHomeRunsText);
        s = "" + g.homeRuns;
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editRBIsText);
        s = "" + g.runsBattedIn;
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editStealsText);
        s = "" + g.steals;
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editWalksText);
        s = "" + g.walks;
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editStrikeoutsText);
        s = "" + g.strikeouts;
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editAtBatsText);
        s = "" + g.atBats;
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editHitsText);
        s = "" + g.hits;
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editRunsScoredText);
        s = g.runsScored +"";
        tv.setText(s);

        tv = (TextView) activity.findViewById(R.id.editNameText);
        s = g.player_name;
        tv.setText(s);


    }

    public void onClick(View view) {
        Intent email = new Intent(Intent.ACTION_SEND);
    }

    public void sendEmail() {
        if (game == null) {
            System.out.println("Fragment's game is null!");
            return;
        }

        Intent intent = new Intent();
        intent.setType("text/plain");
        String subject = "Baseball Recorder: Game on " + game.gameDateStr.substring(0, game.gameDateStr.length() - 5);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getString(R.string.game_date));
//        stringBuilder.append(' ');
        stringBuilder.append(game.gameDateStr);
        stringBuilder.append('\n');

        stringBuilder.append(getString(R.string.player_name));
        stringBuilder.append((game.player_name + '\n'));

        stringBuilder.append(getString(R.string.game_score));
        stringBuilder.append((game.scoreA + " to " + game.scoreB + '\n'));

        stringBuilder.append(getString(R.string.game_at_bats));
        stringBuilder.append(getString(game.atBats + '\n'));

        stringBuilder.append(getString(R.string.runs_scored));
        stringBuilder.append((game.runsScored + '\n'));

        stringBuilder.append(getString(R.string.game_hits));
        stringBuilder.append((game.hits + '\n'));

        stringBuilder.append(getString(R.string.game_steals));
        stringBuilder.append((game.steals + '\n'));

        stringBuilder.append(getString(R.string.singles));
        stringBuilder.append((game.singles + '\n'));

        stringBuilder.append(getString(R.string.doubles));
        stringBuilder.append((game.doubles + '\n'));

        stringBuilder.append(getString(R.string.triples));
        stringBuilder.append((game.triples + '\n'));

        stringBuilder.append(getString(R.string.home_runs));
        stringBuilder.append((game.homeRuns + '\n'));

        stringBuilder.append(getString(R.string.runs_batted_in));
        stringBuilder.append((game.runsBattedIn + '\n'));

        stringBuilder.append(getString(R.string.field_outs));
        stringBuilder.append((game.fieldOuts + '\n'));

        stringBuilder.append(getString(R.string.strikeouts));
        stringBuilder.append((game.strikeouts + '\n'));

        stringBuilder.append(getString(R.string.walks));
        stringBuilder.append((game.walks + '\n'));


        String body = stringBuilder.toString();
        intent.putExtra(Intent.EXTRA_TEXT, body);

        startActivity(Intent.createChooser(intent, "Send Email"));
    }

}

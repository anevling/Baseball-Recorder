package sports_recorder.sportsrecorder;

import android.content.Context;
import android.os.Bundle;
import java.util.Date;


public class Game {
    public String gameDateStr, player_name;

    public int runsScoredA, runsScoredB, atBats, hits, singles, doubles, triples, homeRuns, runsBattedIn, runsScored, strikeouts, walks, steals, scored, fieldOuts, scoreA, scoreB;

    // Constructor
    public Game(Bundle bundle, Context context) {
        if (bundle != null) {
            this.gameDateStr = bundle.getString(context.getString(R.string.saved_game_date), "");
            this.player_name = bundle.getString(context.getString(R.string.saved_player_name), "");

            this.scoreA = bundle.getInt(context.getString((R.string.saved_scoreA)), 0);
            this.scoreB = bundle.getInt(context.getString((R.string.saved_scoreB)), 0);

            this.runsScoredA= bundle.getInt(context.getString((R.string.runs_scoredA)), 0);
            this.runsScoredB = bundle.getInt(context.getString((R.string.runs_scoredB)), 0);
            this.atBats = bundle.getInt(context.getString((R.string.saved_at_bats)), 0);
            this.hits = bundle.getInt(context.getString((R.string.saved_hits)), 0);
            this.singles = bundle.getInt(context.getString((R.string.saved_singles)), 0);
            this.doubles = bundle.getInt(context.getString((R.string.saved_doubles)), 0);

            this.triples = bundle.getInt(context.getString((R.string.saved_triples)), 0);
            this.homeRuns = bundle.getInt(context.getString((R.string.saved_home_runs)), 0);
            this.runsBattedIn = bundle.getInt(context.getString((R.string.saved_RBIs)), 0);
            this.strikeouts = bundle.getInt(context.getString((R.string.saved_strikeouts)), 0);
            this.walks = bundle.getInt(context.getString((R.string.saved_walks)), 0);
            this.steals = bundle.getInt(context.getString((R.string.saved_steals)), 0);
            this.scored = bundle.getInt(context.getString((R.string.scored)), 0);
            this.fieldOuts = bundle.getInt(context.getString((R.string.saved_field_outs)), 0);

            this.runsScored= bundle.getInt(context.getString((R.string.saved_runs_scored)), 0);

        }


    }

    public String toString() {
        return gameDateStr;
    }

}

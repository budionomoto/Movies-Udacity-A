package id.xyzsystem.budiono.moviesudacitya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class gDetail extends AppCompatActivity {
    @BindView(R.id.detail_poster)
    ImageView mDetailPoster;

    @BindView(R.id.detail_title)
    TextView mDetailTitle;

    @BindView(R.id.detail_overview)
    TextView mDetailOverview;

    @BindView(R.id.detail_rating)
    TextView mDetailRating;

    @BindView(R.id.detail_release)
    TextView mDetailRelease;

    @BindView(R.id.detail_overview_title)
    TextView mDetailOverviewTitle;

    @BindView(R.id.detail_release_title)
    TextView mDetailReleaseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_detail);

        dModel movie = aApp.getInstance().getGson().fromJson(this.getIntent().getStringExtra(fFunction.Data.MOVIE_INTENT), dModel.class);

        initView();

        setTitle(movie.getTitle());

        fFunction.Function.setImageResource(this, bInterface.BASE_URL_IMAGE + movie.getPosterPath(), mDetailPoster);

        mDetailTitle.setText(movie.getTitle());

        if (movie.getOverview() != null && !movie.getOverview().equals("")) {
            mDetailOverview.setText(movie.getOverview());
            mDetailOverviewTitle.setVisibility(View.VISIBLE);
        } else {
            mDetailOverview.setText("");
            mDetailOverviewTitle.setVisibility(View.GONE);
        }

        String ratingStr = String.valueOf(movie.getVoteAverage()) + getString(R.string.detail_rating_of);
        mDetailRating.setText(ratingStr);

        try {
            mDetailRelease.setText(new SimpleDateFormat(fFunction.Data.MOVIE_RELEASE_DATE_FORMAT_AFTER, Locale.getDefault()).format(new SimpleDateFormat(fFunction.Data.MOVIE_RELEASE_DATE_FORMAT_BEFORE, Locale.getDefault()).parse(movie.getReleaseDate())));
            mDetailReleaseTitle.setVisibility(View.VISIBLE);
        } catch (ParseException e) {
            e.printStackTrace();
            mDetailRelease.setText("");
            mDetailReleaseTitle.setVisibility(View.GONE);
        }

    }
    private void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package id.xyzsystem.budiono.moviesudacitya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv_main_movies)
    RecyclerView mMainMovies;

    @BindView(R.id.pb_loading_bar)
    ProgressBar loadingBar;

    @BindView(R.id.error_layout)
    LinearLayout errorLayout;

    private EventBus eventBus;
    private eAdapter adapter;
    private hController controller;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new hController();

        initView();

        errorLayout.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);

        page = 1;
        setPopularMovies(page);

    }
    private void initView() {
        ButterKnife.bind(this);

        int columns = fFunction.Function.getColumnsCount(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, columns);
        mMainMovies.setLayoutManager(layoutManager);
        mMainMovies.setHasFixedSize(true);

        adapter = new eAdapter();
        mMainMovies.setAdapter(adapter);
    }

    private void setPopularMovies(int page) {
        MainActivity.this.setTitle(getString(R.string.popular_movies));

        this.page = page;
        controller.getPopularMovies(page);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventBus = aApp.getInstance().getEventBus();
        eventBus.register(this);
    }

    @Override
    protected void onPause() {
        eventBus.unregister(this);
        super.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMovieList(iEvent event){
        loadingBar.setVisibility(View.GONE);
        adapter.setData(event.getBody().getResults());
        mMainMovies.scrollToPosition(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMovieListError(kErrorEvent event) {
        loadingBar.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
        Log.e("errorResultData", event.getMessage());
    }


}

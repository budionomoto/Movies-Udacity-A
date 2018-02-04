package id.xyzsystem.budiono.moviesudacitya;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by budiono on 03/02/18.
 */

public class hController {
    private EventBus eventBus = aApp.getInstance().getEventBus();

    private void getMovies(int type, int page) {
        Call<cResponse> movieResponseCall = aApp.getInstance().getApiService().getMovies(fFunction.Data.MOVIE_LIST_TYPE[type], bInterface.API_KEY, bInterface.LANG_SOURCE, page, bInterface.MOVIES_REGION);
        movieResponseCall.enqueue(new Callback<cResponse>() {
            @Override
            public void onResponse(Call<cResponse> call, Response<cResponse> response) {
                if (response.code() == 200) {
                    eventBus.post(new iEvent(response.message(), response.body()));
                } else {
                    eventBus.post(new kErrorEvent(response.message()));
                }
            }

            @Override
            public void onFailure(Call<cResponse> call, Throwable t) {
                eventBus.post(new kErrorEvent(t.getMessage()));
            }
        });
    }

    public void getPopularMovies(int page) {
        getMovies(0, page);
    }

    public void getTopRatedMovies(int page) {
        getMovies(1, page);
    }
}

package id.xyzsystem.budiono.moviesudacitya;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by budiono on 30/01/18.
 */

public interface bInterface {
    String BASE_URL = "https://api.themoviedb.org/3/";
    String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w185";
    String API_KEY = "02c6bc54ddd867f7bcf339600faa7263";
    String LANG_SOURCE = "en-US";
    String MOVIES_REGION = "US";

    @GET("movie/{type}")
    Call<cResponse> getMovies(@Path("type") String type, @Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page, @Query("region") String region);

}

package id.xyzsystem.budiono.moviesudacitya;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by budiono on 30/01/18.
 */
// todo (1) membuat App
public class aApp  extends Application {
    private static aApp instance;
    private Retrofit retrofit;
    private EventBus eventBus;
    private Gson gson;

    private static String BASE_URL;

    public aApp() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createEventBus();
        createRetrofitClient();
        createGson();
    }

    private void createGson() {
        gson = new GsonBuilder().create();
    }

    private void createEventBus() {
        eventBus = EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .build();
    }

    private void createRetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(bInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static aApp getInstance() {
        return instance;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Retrofit getRetrofitClient() {
        return retrofit;
    }

    public Gson getGson() {
        return gson;
    }

    public bInterface getApiService() {
        return getRetrofitClient().create(bInterface.class);
    }
}

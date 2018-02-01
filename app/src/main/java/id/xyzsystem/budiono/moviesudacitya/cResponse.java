package id.xyzsystem.budiono.moviesudacitya;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by budiono on 30/01/18.
 */

public class cResponse {
    @SerializedName("results")
    private List<dModel> results;

    public List<dModel> getResults() {
        return results;
    }
}

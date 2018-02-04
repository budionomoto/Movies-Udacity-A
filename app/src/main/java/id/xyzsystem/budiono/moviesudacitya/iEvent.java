package id.xyzsystem.budiono.moviesudacitya;

/**
 * Created by budiono on 03/02/18.
 */

public class iEvent extends jBaseEvent {
    private cResponse body;

    public iEvent(String message, cResponse body) {
        super(message);
        this.body = body;
    }

    public cResponse getBody() {
        return body;
    }

}

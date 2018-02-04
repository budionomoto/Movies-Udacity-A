package id.xyzsystem.budiono.moviesudacitya;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by budiono on 03/02/18.
 */

public class eAdapter extends RecyclerView.Adapter<eAdapter.MoviesViewHolder> {
    private ArrayList<dModel> movies;

    public eAdapter() {
        movies = new ArrayList<>();
    }

    public void setData(List<dModel> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_e, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesViewHolder holder, int position) {
        fFunction.Function.setImageResource(holder.itemView.getContext(), bInterface.BASE_URL_IMAGE + movies.get(position).getPosterPath(), holder.movie_poster);
    }

    private void detailMovie(Context context, int position) {
        Intent i = new Intent(context, gDetail.class);
        i.putExtra(fFunction.Data.MOVIE_INTENT, aApp.getInstance().getGson().toJson(movies.get(position)));
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_movies_poster)
        ImageView movie_poster;

        public MoviesViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            movie_poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailMovie(itemView.getContext(), getAdapterPosition());
                }
            });
        }
    }
}

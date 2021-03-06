package com.androcid.zomato.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androcid.zomato.R;
import com.androcid.zomato.model.RestaurantImage;
import com.androcid.zomato.retro.RetroInterface;
import com.androcid.zomato.util.MyFont;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Androcid on 12/27/2016.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private static final String TAG = PhotosAdapter.class.getSimpleName();
    Context context;
    MyFont myFont;
    private List<RestaurantImage> list;
    private ClickListener clickListener;

    public PhotosAdapter(Context context, List<RestaurantImage> list) {
        this.list = list;
        this.context = context;
        myFont = new MyFont(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photos, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RestaurantImage item = list.get(position);

        if(item.getImage()!=null && !item.getImage().equals("")) {
            Picasso.with(context)
                    .load(RetroInterface.IMAGE_URL+item.getImage())
                    .resize(200,200)
                    .placeholder(R.drawable.placeholder_200)
                    .error(R.drawable.placeholder_200)
                    .into(holder.image);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (clickListener != null) {
                    clickListener.onItemClickListener(view, position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refresh(List<RestaurantImage> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        public void onItemClickListener(View v, int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }

}

package com.example.pufflemafia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private int[] imageIds = {R.drawable.click_start, R.drawable.enter_names, R.drawable.delete_names,R.drawable.choose_characters,R.drawable.tap4characters,R.drawable.top2deletecharacters,R.drawable.start_night,R.drawable.chooshe_character,R.drawable.next_action,R.drawable.end_night,R.drawable.kill_player,R.drawable.edit_role,R.drawable.edit_name,R.drawable.edit_tokens,};

    public ImagePagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_image, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(imageIds[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}


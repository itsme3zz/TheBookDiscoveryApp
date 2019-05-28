package com.example.android.thebookdiscoveryapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] images = {R.drawable.slide10,R.drawable.slide7,R.drawable.slide9,R.drawable.slide8};
    private String [] headings = {

            "There is a Reader Inside Everyone.",
            "Meet Your New Favourite Book!",
            "Too Many Books to Choose From?",
            "How We Do it"
    };

    private String [] desc = {

            "We Bring Out The Reader Inside Of You\n By Giving You A Selection of Books That\n Will Interest You.",
            "We'll Introduce You To Books That Will \nGo Well With Your Personality.",
            "You Won't Have To Look Through a lot of \nBooks, We'll Narrow it Down for You.",
            "Just Answer a Few Questions, And Leave \nThe Rest To us. "
    };



    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        TextView textView2 = view.findViewById(R.id.textView2);
        imageView.setImageResource(images[position]);
        textView.setText(headings[position]);
        textView2.setText(desc[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((View) object);


        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}

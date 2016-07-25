package com.stone.beautyshare.views.widget;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stone.beautyshare.R;
import com.stone.beautyshare.config.Classify;
import com.utils.singleton.SingleTonVolley;
import com.utils.views.RoundImageView;

import java.util.List;

/**
 * Created by junwei on 15/7/26.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Classify.Photos> photosList;
    private LayoutInflater inflater;
    private Handler d;

    public GridViewAdapter(Context c, List<Classify.Photos> photosList) {
        mContext = c;
        this.photosList = photosList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return photosList.size();
    }

    @Override
    public Object getItem(int position) {
        return photosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Classify.Photos photos = (Classify.Photos)getItem(position);
        if (convertView == null) {//new
            View view = (View)inflater.inflate(R.layout.gv_item_layout, null);
            RoundImageView roundImageView=(RoundImageView)view.findViewById(R.id.gv_image);
            roundImageView.setTag("url");
            convertView=view;
        } else {
        }
        //            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.gv_name_ly);
        ImageView newTip=(ImageView)convertView.findViewById(R.id.gv_new_tip);
        if(photos.isnew>0){
            newTip.setVisibility(View.VISIBLE);
        }
        RoundImageView roundImageView=(RoundImageView)convertView.findViewById(R.id.gv_image);
        roundImageView.setImageUrl(photos.baseUrl+photos.pics.get(0).url, SingleTonVolley.getInstance(mContext).getImageLoader());
        TextView textView = (TextView) convertView.findViewById(R.id.gv_name);
        textView.setText(">>"+photos.name+"("+photos.pics.size()+"p)");
        return convertView;
    }
}

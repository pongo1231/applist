package com.pongo1231.applist.Adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pongo1231.applist.R;
import com.pongo1231.applist.models.App;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {
    private App[] appList;

    public AppListAdapter(App[] appList) {
        this.appList = appList;
    }

    /**
     * Viewholder which provides a reference for each view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView appNameView;
        public final ImageView appIconView;
        public final TextView appVersionView;

        public ViewHolder(View appListItemView) {
            super(appListItemView);

            appNameView = appListItemView.findViewById(R.id.activity_list_applistitem_appname);
            appIconView = appListItemView.findViewById(R.id.activity_list_applistitem_appicon);
            appVersionView = appListItemView.findViewById(R.id.activity_list_applistitem_appversion);
        }
    }

    /**
     * Inflate custom layout on item create
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View appListItemView = inflater.inflate(R.layout.activity_list_applistitem, parent, false);

        return new ViewHolder(appListItemView);
    }

    /**
     * Set content of item
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        App app = appList[position];

        String appName = app.getName();
        holder.appNameView.setText(appName);

        Drawable appIcon = app.getIcon();
        holder.appIconView.setImageDrawable(appIcon);

        String appVersion = app.getVersion();
        holder.appVersionView.setText(appVersion);
    }

    /**
     * Provides adapter with amount of items
     */
    @Override
    public int getItemCount() {
        return appList.length;
    }
}

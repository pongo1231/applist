package com.pongo1231.applist;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pongo1231.applist.Adapters.AppListAdapter;
import com.pongo1231.applist.models.App;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView appListView;

    /**
     * onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initAppList();
    }

    /**
     * Initializes the app list
     */
    private void initAppList() {
        appListView = findViewById(R.id.activity_list_applist);
        appListView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        appListView.setLayoutManager(layoutManager);

        // Add dividers
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(appListView.getContext(),
                layoutManager.getOrientation());
        appListView.addItemDecoration(dividerItemDecoration);

        AppListAdapter adapter = new AppListAdapter(getInstalledApps());
        appListView.setAdapter(adapter);
    }

    /**
     * @return A list of all apps sorted alphabetically
     */
    private App[] getInstalledApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = getPackageManager().queryIntentActivities( mainIntent, 0);

        App[] finalApps = new App[apps.size()];
        for (int i = 0; i < apps.size(); i++) {
            ResolveInfo app = apps.get(i);
            PackageManager pm = getPackageManager();

            String name = app.loadLabel(pm).toString();
            Drawable icon = app.loadIcon(pm);
            String version = "Unknown Version";
            try {
                version = getAppVersion(app.activityInfo.packageName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            finalApps[i] = new App(name, icon, version);
        }

       sortAppList(finalApps);
        return finalApps;
    }

    /**
     * @param packageName Package name of the app
     * @return Version of the app
     */
    private String getAppVersion(String packageName) throws PackageManager.NameNotFoundException {
        return getPackageManager().getPackageInfo(packageName, 0).versionName;
    }

    /**
     * Sorts an array full of apps
     * @param apps App list
     */
    private void sortAppList(App[] apps) {
        Arrays.sort(apps, new Comparator<App>() {
            @Override
            public int compare(App app1, App app2) {
                return app1.getName().compareTo(app2.getName());
            }
        });
    }
}

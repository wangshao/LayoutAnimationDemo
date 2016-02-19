package wangshao.slidingdemo;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    View map, sidebar, homeclick,
            sideplaceholder,
            homeplaceholder,

    home_homeplaceholder,
            home_homeplaceholder2,
            home_sideplaceholder,

    side_sideplaceholder;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = findViewById(R.id.map);
        sidebar = findViewById(R.id.sideBar);
        homeclick = findViewById(R.id.homeClick);
        sideplaceholder = findViewById(R.id.sideBarPlaceHolder);
        homeplaceholder = findViewById(R.id.homeplaceholder);

        home_homeplaceholder = findViewById(R.id.home_homePlaceHolder);
        home_homeplaceholder2 = findViewById(R.id.home_homePlaceHolder2);
        home_sideplaceholder = findViewById(R.id.home_sidePlaceHolder);

        side_sideplaceholder = findViewById(R.id.side_sidePlaceHolder);

        LinearLayout roots[] =
                {
                ((LinearLayout) findViewById(R.id.mapContainer)),
                ((LinearLayout) findViewById(R.id.subroot)),
                ((LinearLayout) findViewById(R.id.subroot1)),
                ((LinearLayout) findViewById(R.id.map_subroot)),
                ((LinearLayout) findViewById(R.id.sub_subroot)),
                ((LinearLayout) findViewById(R.id.sub_subroot1))
        };

        int i = 0;


        for (LinearLayout root : roots) {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            try {

                root.getLayoutTransition().setDuration(200);
                root.getLayoutTransition().setStartDelay(LayoutTransition.DISAPPEARING, 0);
                root.getLayoutTransition().setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);
                root.getLayoutTransition().setStartDelay(LayoutTransition.APPEARING, 0);
                root.getLayoutTransition().setStartDelay(LayoutTransition.CHANGE_APPEARING, 0);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            i++;
        }
        init();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int height = 0;
//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int actionBarHeight = 0;
//        TypedValue tv = new TypedValue();
//        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
//            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
//        }
//         = size.y-actionBarHeight-100;
        height = findViewById(R.id.root).getHeight();
        LinearLayout[] roots = {
                ((LinearLayout) findViewById(R.id.map_subroot)),
                ((LinearLayout) findViewById(R.id.sub_subroot))
        };
        for(LinearLayout root : roots) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) root.getLayoutParams();
            marginLayoutParams.bottomMargin = -height / 2;
            System.out.println("height" + root.getHeight());
            root.setLayoutParams(marginLayoutParams);
        }

        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "初始化");
        menu.add(0, 2, 0, "open side bar");
        menu.add(0, 3, 0, "close side bar");
        menu.add(0, 4, 0, "open homeClick");
        menu.add(0, 5, 0, "close homeClick");
        menu.add(0, 6, 0, "zoom in");
        menu.add(0, 7, 0, "zoom out");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                init();
                break;
            case 2:
                openSideBar();
                break;
            case 3:
                closeSideBar();
                break;
            case 4:
                openHome();
                break;
            case 5:
                closeHome();
                break;
            case 6:
                zoomin();
                break;
            case 7:
                zoomout();
                break;
        }

        return true;
    }

    void init() {
        closeHome();
        closeSideBar();
    }

    void openSideBar() {
        sideplaceholder.setVisibility(View.VISIBLE);
        home_sideplaceholder.setVisibility(View.VISIBLE);
        side_sideplaceholder.setVisibility(View.VISIBLE);

    }

    void closeSideBar() {
        sideplaceholder.setVisibility(View.GONE);
        side_sideplaceholder.setVisibility(View.GONE);
        home_sideplaceholder.setVisibility(View.GONE);

    }

    void openHome() {
        home_homeplaceholder2.setVisibility(View.GONE);
        homeplaceholder.setVisibility(View.VISIBLE);
    }

    void closeHome() {
        homeplaceholder.setVisibility(View.GONE);
        home_homeplaceholder.setVisibility(View.VISIBLE);
        home_homeplaceholder2.setVisibility(View.VISIBLE);
    }

    void zoomin() {
        closeSideBar();
        homeplaceholder.setVisibility(View.GONE);
        home_homeplaceholder.setVisibility(View.GONE);
        home_homeplaceholder2.setVisibility(View.GONE);
    }

    void zoomout() {
        openSideBar();
        homeplaceholder.setVisibility(View.VISIBLE);
        sideplaceholder.setVisibility(View.VISIBLE);

        home_homeplaceholder.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://wangshao.slidingdemo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://wangshao.slidingdemo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

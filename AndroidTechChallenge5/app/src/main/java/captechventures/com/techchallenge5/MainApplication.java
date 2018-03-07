package captechventures.com.techchallenge5;

import android.app.Application;
import android.util.Log;

/**
 * Created by mluansing on 11/27/17.
 */

public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "TechChallenge5 started!");
//        setTheme(R.style.NoActionBar);
    }

    /**
     * single activity application
     * themed screens (consider screen size + orientation)
     * toggle + reload theme at runtime
     * must support up to xhdpi density
     *
     * understand:
     * density, orientation, size
     * themes + styling
     *
     * new API 11+
     *
     * old API <11
     *
     */

}

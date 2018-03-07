package captechventures.com.techchallenge5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.lang.reflect.Method;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // layout variables
//    private Toolbar toolbar;

    // track current theme
    private final String KEY_THEME = "KEY_THEME", PREFS_NAME = "PREFS_NAME";
    private final Boolean DARK_THEME = false, LIGHT_THEME = true;
    private Boolean theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Use the chosen theme
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        theme = preferences.getBoolean(KEY_THEME, false);

        if (theme) {
            Log.i(TAG, "Setting up activity theme to light theme");
            setTheme(R.style.LightTheme);
        } else {
            Log.i(TAG, "Setting up activity theme to dark theme");
            setTheme(R.style.DarkTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toggleButton:
                Log.d(TAG, "Theme toggle button clicked!");
                if (theme == null) {
                    Log.e(TAG, "Theme toggle variable incorrect");
                    return false;
                } else if (theme) { // light --> dark
                    Log.i(TAG, "Switching to dark theme");
                    toggleTheme(DARK_THEME);
                } else { // dark --> light
                    Log.i(TAG, "Switching to light theme");
                    toggleTheme(LIGHT_THEME);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(KEY_THEME, darkTheme);
        editor.apply();

        Intent intent = getIntent();
        finish();

        startActivity(intent);
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    // helper method to get theme ID
    int getThemeId() {
        try {
            Class<?> wrapper = Context.class;
            Method method = wrapper.getMethod("getThemeResId");
            method.setAccessible(true);
            return (Integer) method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}

package futsal.yo.com.yofutsal.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rizalsidikp on 17/04/18.
 */

public class AccessToken {
    private final static String SHARED_PREF_NAME = "ACCESS_TOKEN";
    private final static String TOKEN_KEY = "TOKEN_KEY";

    public static String getToken(Context c) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(TOKEN_KEY, "");
    }

    public static void setToken(Context c, String token) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN_KEY, "Bearer " + token);
        editor.apply();
    }

    public  static void removeToken(Context c) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(TOKEN_KEY);
        editor.commit();
        editor.apply();
    }
}

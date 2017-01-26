package hr.karlovrbic.notify.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import hr.karlovrbic.notify.model.User;

import static java.lang.Long.getLong;

/**
 * Created by thekarlo95 on 23.01.17..
 */

public final class SharedPrefsUtils {

    private static final String PREFRENCES_NAME_LOGIN = "hr.karlovrbic.notify.LOGIN";

    private static final String USER_ID = "id";
    private static final String USER_TOKEN = "fcm_token";
    private static final String USER_USERNAME = "username";
    private static final String USER_EMAIL = "email";
    private static final String USER_NAME = "name";
    private static final String USER_SURNAME = "surname";
    private static final String USER_BIRTHDAY = "birthday";
    private static final String USER_CREATED_AT = "created_at";
    private static final String USER_PROFILE_PICTURE = "profile_picture_link";

    public static void saveUser(Context context, User user) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFRENCES_NAME_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putLong(USER_ID, user.getId());
        editor.putString(USER_TOKEN, user.getToken());
        editor.putString(USER_USERNAME, user.getUsername());
        editor.putString(USER_EMAIL, user.getEmail());
        editor.putString(USER_NAME, user.getName());
        editor.putString(USER_SURNAME, user.getSurname());
        editor.putLong(USER_BIRTHDAY, user.getBirthDay().getTime());
        editor.putLong(USER_CREATED_AT, user.getCreatedAt().getTime());
        editor.putString(USER_PROFILE_PICTURE, user.getProfilePictureLink());

        editor.apply();
    }

    public static User getUser(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFRENCES_NAME_LOGIN, Context.MODE_PRIVATE);

        long id = sharedPref.getLong(USER_ID, 0L);
        String token = sharedPref.getString(USER_TOKEN, null);
        String username = sharedPref.getString(USER_USERNAME, null);
        String email = sharedPref.getString(USER_EMAIL, null);
        String name = sharedPref.getString(USER_NAME, null);
        String surname = sharedPref.getString(USER_SURNAME, null);
        Date birthday = new Date(getLong(USER_BIRTHDAY, 0L));
        Date createdAt = new Date(getLong(USER_CREATED_AT, 0L));
        String profilePicture = sharedPref.getString(USER_PROFILE_PICTURE, null);

        return new User(id, token, username, email, name, surname, birthday, createdAt, profilePicture, null);
    }

    public static void removeUser(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFRENCES_NAME_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.remove(USER_ID);
        editor.remove(USER_TOKEN);
        editor.remove(USER_USERNAME);
        editor.remove(USER_EMAIL);
        editor.remove(USER_NAME);
        editor.remove(USER_SURNAME);
        editor.remove(USER_BIRTHDAY);
        editor.remove(USER_CREATED_AT);
        editor.remove(USER_PROFILE_PICTURE);

        editor.apply();
    }

    public static Long getUserId(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFRENCES_NAME_LOGIN, Context.MODE_PRIVATE);

        return sharedPref.getLong(USER_ID, 0L);
    }
}

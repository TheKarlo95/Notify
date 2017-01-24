package hr.karlovrbic.notify.services.firebase.instanceid;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import javax.inject.Inject;

import hr.karlovrbic.notify.AndroidApplication;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.modules.FirebaseInstanceIdModule;
import hr.karlovrbic.notify.utils.SharedPrefsUtils;

/**
 * Created by thekarlo95 on 23.01.17..
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService implements IFirebaseInstanceId.View {

    private static final String TAG = "Firebase";

    @Inject
    IFirebaseInstanceId.Presenter presenter;

    @Override
    public void onCreate() {
        super.onCreate();

        injectDependencies(((AndroidApplication) getApplication()).getApplicationComponent());
    }

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New token: " + token);

        // TO DO update token on server
        Long userId = SharedPrefsUtils.getUserId(getApplicationContext());
        presenter.updateToken(userId, token);
    }

    @Override
    public void log(String tag, String message) {
        Log.d(tag, message);
    }

    private void injectDependencies(AppComponent appComponent){
        appComponent.plus(new FirebaseInstanceIdModule(this)).inject(this);
    }
}

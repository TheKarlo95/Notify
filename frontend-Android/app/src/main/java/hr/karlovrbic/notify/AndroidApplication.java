package hr.karlovrbic.notify;

import android.app.Application;

import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.components.DaggerAppComponent;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class AndroidApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }
}

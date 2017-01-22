package hr.karlovrbic.notify.dagger.modules.base;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.BuildConfig;
import okhttp3.HttpUrl;

/**
 * Created by TheKarlo95 on 23.10.2016..
 */
@Module
public class HostModule {

    private static final int DEBUG_NETWORK_TIMEOUT_SECONDS = 60;
    private static final int NETWORK_TIMEOUT_SECONDS = 5;
    private static final String API_URL = "https://notify-v1.herokuapp.com/api/v1/";

    @Provides
    @Singleton
    public HttpUrl provideEndpoint() {
        return HttpUrl.parse(API_URL);
    }

    @Provides
    @Singleton
    public Integer provideNetworkTimeout() {
        if (BuildConfig.DEBUG) {
            return DEBUG_NETWORK_TIMEOUT_SECONDS;
        } else {
            return NETWORK_TIMEOUT_SECONDS;
        }
    }
}

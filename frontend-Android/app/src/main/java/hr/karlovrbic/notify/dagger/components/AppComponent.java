package hr.karlovrbic.notify.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import hr.karlovrbic.notify.dagger.modules.EventListModule;
import hr.karlovrbic.notify.dagger.modules.EventModule;
import hr.karlovrbic.notify.dagger.modules.FollowedEventListModule;
import hr.karlovrbic.notify.dagger.modules.LoginModule;
import hr.karlovrbic.notify.dagger.modules.MainModule;
import hr.karlovrbic.notify.dagger.modules.MyEventsModule;
import hr.karlovrbic.notify.dagger.modules.SignUpModule;
import hr.karlovrbic.notify.dagger.modules.UserListModule;
import hr.karlovrbic.notify.dagger.modules.UserProfileModule;
import hr.karlovrbic.notify.dagger.modules.base.ApiModule;
import hr.karlovrbic.notify.dagger.modules.base.ClientModule;
import hr.karlovrbic.notify.dagger.modules.base.ConverterModule;
import hr.karlovrbic.notify.dagger.modules.base.HostModule;
import hr.karlovrbic.notify.dagger.modules.base.LogModule;

/**
 * Created by TheKarlo95 on 23.10.2016..
 */
@Component(modules = {
        ApiModule.class,
        ClientModule.class,
        ConverterModule.class,
        HostModule.class,
        LogModule.class
})
@Singleton
public interface AppComponent {
    LoginComponent plus(LoginModule module);

    SignUpComponent plus(SignUpModule module);

    MainComponent plus(MainModule module);

    UserListComponent plus(UserListModule module);

    UserProfileComponent plus(UserProfileModule module);

    EventListComponent plus(EventListModule module);

    FollowedEventListComponent plus(FollowedEventListModule module);

    MyEventsComponent plus(MyEventsModule module);

    EventComponent plus(EventModule module);
}

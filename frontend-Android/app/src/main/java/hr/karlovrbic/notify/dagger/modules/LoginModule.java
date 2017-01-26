package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.features.users.login.ILogin;
import hr.karlovrbic.notify.features.users.login.LoginPresenter;
import hr.karlovrbic.notify.features.users.login.interactors.LoginInteractor;
import hr.karlovrbic.notify.services.firebase.instanceid.IFirebaseInstanceId;
import hr.karlovrbic.notify.services.firebase.instanceid.interactors.TokenUpdateInteractor;

/**
 * Created by thekarlo95 on 21.01.17..
 */
@Module
public class LoginModule {

    private ILogin.View view;

    public LoginModule(ILogin.View view) {
        this.view = view;
    }

    @Provides
    public ILogin.View provideView() {
        return view;
    }

    @Provides
    public ILogin.Presenter providePresenter(LoginPresenter presenter) {
        return presenter;
    }

    @Provides
    public ILogin.Interactor provideInteractor(LoginInteractor interactor) {
        return interactor;
    }

    @Provides
    public IFirebaseInstanceId.TokenUpdateInteractor provideTokenUpdateInteractor(TokenUpdateInteractor interactor) {
        return interactor;
    }
}

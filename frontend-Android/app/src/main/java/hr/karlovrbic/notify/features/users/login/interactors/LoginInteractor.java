package hr.karlovrbic.notify.features.users.login.interactors;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.features.users.login.ILogin;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.model.UserLogin;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class LoginInteractor extends BaseInteractor<User, UserLogin> implements ILogin.Interactor {

    private ApiService service;

    @Inject
    public LoginInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<User> createObservable(UserLogin userLogin) {
        return service.login(userLogin);
    }
}

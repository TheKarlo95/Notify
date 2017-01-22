package hr.karlovrbic.notify.features.users.signup.interactors;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.features.users.signup.ISignUp;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.model.UserSignUp;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class SignUpInteractor extends BaseInteractor<User, UserSignUp> implements ISignUp.Interactor {

    private ApiService service;

    @Inject
    public SignUpInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<User> createObservable(UserSignUp userSignUp) {
        return service.signUp(userSignUp);
    }
}

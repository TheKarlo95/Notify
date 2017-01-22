package hr.karlovrbic.notify.features.users.login;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.model.UserLogin;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class LoginPresenter implements ILogin.Presenter {

    private ILogin.View view;
    private ILogin.Interactor interactor;

    @Inject
    public LoginPresenter(ILogin.View view, ILogin.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void login(@NonNull String username, @NonNull String password) {
        UserLogin userLogin = new UserLogin(username, password);
        view.showLoading("Logging in…");
        interactor.execute(new DisposableObserver<User>() {
            @Override
            public void onNext(User value) {
                view.loginSuccessful(value.getId());
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.showMessage("Error happened while logging in");
            }

            @Override
            public void onComplete() {
            }
        }, userLogin);
    }

    @Override
    public void cancel() {
        view.hideLoading();
        interactor.cancel();
    }
}

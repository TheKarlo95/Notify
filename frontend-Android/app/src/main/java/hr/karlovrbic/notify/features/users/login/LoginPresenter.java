package hr.karlovrbic.notify.features.users.login;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import hr.karlovrbic.notify.model.TokenUpdate;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.model.UserLogin;
import hr.karlovrbic.notify.services.firebase.instanceid.IFirebaseInstanceId;
import hr.karlovrbic.notify.services.firebase.instanceid.interactors.TokenUpdateInteractor;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class LoginPresenter implements ILogin.Presenter {

    private static final String FIREBASE_TAG = "Firebase";

    private ILogin.View view;
    private ILogin.Interactor interactor;
    private IFirebaseInstanceId.TokenUpdateInteractor tokenUpdateInteractor;

    @Inject
    public LoginPresenter(ILogin.View view,
                          ILogin.Interactor interactor,
                          IFirebaseInstanceId.TokenUpdateInteractor tokenUpdateInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.tokenUpdateInteractor = tokenUpdateInteractor;
    }

    @Override
    public void login(@NonNull String username, @NonNull String password) {
        UserLogin userLogin = new UserLogin(username, password);
        view.showLoading("Logging in…");
        interactor.execute(new DisposableObserver<User>() {
            @Override
            public void onNext(User value) {
                view.loginSuccessful(value);
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
    public void updateToken(Long userId, String token) {
        if(userId == null || userId <= 0L) {
            Log.d(FIREBASE_TAG, "User ID cannot be null or lower than 1. User is probably not logged in.");
        } else if (token == null || token.isEmpty()) {
            Log.d(FIREBASE_TAG, "Token cannot be null");
        } else {
            TokenUpdateInteractor.Params params = new TokenUpdateInteractor.Params(userId, new TokenUpdate(token));
            tokenUpdateInteractor.execute(new DisposableObserver<User>() {
                @Override
                public void onNext(User user) {
                    Log.d(FIREBASE_TAG, "Token updated");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(FIREBASE_TAG, "Error happened while updating token");
                }

                @Override
                public void onComplete() {
                }
            }, params);
        }
    }

    @Override
    public void cancel() {
        view.hideLoading();
        interactor.cancel();
    }
}

package hr.karlovrbic.notify.features.users.signup;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.model.UserSignUp;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class SignUpPresenter implements ISignUp.Presenter {

    private ISignUp.View view;
    private ISignUp.Interactor interactor;

    @Inject
    public SignUpPresenter(ISignUp.View view, ISignUp.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void signUp(@NonNull String username,
                       @NonNull String password,
                       @NonNull String passwordConfirmation,
                       @NonNull String email,
                       @NonNull String name,
                       @NonNull String surname,
                       @NonNull String birthDay) {
        UserSignUp userSignUp = new UserSignUp(username,
                password,
                passwordConfirmation,
                email,
                name,
                surname,
                toDate(birthDay));
        view.showLoading("Signing up…");
        interactor.execute(new DisposableObserver<User>() {
            @Override
            public void onNext(User user) {
                if (user != null) {
                    view.signUpSuccessful();
                }
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                if (!(e instanceof NullPointerException)) {
                    view.showMessage("Error happened while signing up");
                }
            }

            @Override
            public void onComplete() {
            }
        }, userSignUp);
    }

    @Override
    public void cancel() {
        view.hideLoading();
        interactor.cancel();
    }

    private Date toDate(@NonNull String birthday) {
        String[] split = birthday.split("\\.");
        Calendar c = Calendar.getInstance();

        c.set(Integer.parseInt(split[2]),
                Integer.parseInt(split[1]) - 1,
                Integer.parseInt(split[0]),
                0,
                0);
        return c.getTime();
    }
}

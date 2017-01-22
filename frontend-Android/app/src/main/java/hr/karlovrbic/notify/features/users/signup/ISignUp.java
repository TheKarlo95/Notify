package hr.karlovrbic.notify.features.users.signup;

import android.support.annotation.NonNull;

import hr.karlovrbic.notify.features.shared.IBase;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.model.UserSignUp;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public interface ISignUp extends IBase {

    interface View extends IBase.View {
        void signUpSuccessful();
    }

    interface Presenter extends IBase.Presenter {
        void signUp(@NonNull String username,
                   @NonNull String password,
                   @NonNull String passwordConfirmation,
                   @NonNull String email,
                   @NonNull String name,
                   @NonNull String surname,
                   @NonNull String birthDay);
    }

    interface Interactor extends IBase.Interactor<User, UserSignUp> {
    }
}

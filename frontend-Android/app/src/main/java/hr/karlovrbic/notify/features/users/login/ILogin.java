package hr.karlovrbic.notify.features.users.login;

import android.support.annotation.NonNull;

import hr.karlovrbic.notify.features.shared.IBase;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.model.UserLogin;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public interface ILogin extends IBase {

    interface View extends IBase.View {
        void loginSuccessful(User user);
    }

    interface Presenter extends IBase.Presenter {
        void login(@NonNull String username, @NonNull String password);

        void updateToken(Long userId, String token);
    }

    interface Interactor extends IBase.Interactor<User, UserLogin> {
    }
}

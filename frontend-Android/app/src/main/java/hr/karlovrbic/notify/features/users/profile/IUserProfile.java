package hr.karlovrbic.notify.features.users.profile;

import hr.karlovrbic.notify.features.shared.IBase;
import hr.karlovrbic.notify.model.User;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public interface IUserProfile extends IBase {

    interface View extends IBase.View {
        void showUser(User user);
    }

    interface Presenter extends IBase.Presenter {
        void getUser(long userId);
    }

    interface Interactor extends IBase.Interactor<User, Long> {
    }
}

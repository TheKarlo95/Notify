package hr.karlovrbic.notify.features.users.list;

import java.util.ArrayList;
import java.util.List;

import hr.karlovrbic.notify.features.shared.IBase;
import hr.karlovrbic.notify.model.User;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public interface IUserList extends IBase {

    interface View extends IBase.View {
        void showUsers(ArrayList<User> users);

        void addUsers(ArrayList<User> users);

        void clear();
    }

    interface Presenter extends IBase.Presenter {
        void getUserList();
    }

    interface Interactor extends IBase.Interactor<List<User>, Void> {
    }
}

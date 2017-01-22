package hr.karlovrbic.notify.features.users.list;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hr.karlovrbic.notify.model.User;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class UserListPresenter implements IUserList.Presenter {

    private IUserList.View view;
    private IUserList.Interactor interactor;

    @Inject
    public UserListPresenter(IUserList.View view, IUserList.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getUserList() {
        view.showLoading("Loading user list…");
        interactor.execute(new DisposableObserver<List<User>>() {
            @Override
            public void onNext(List<User> users) {
                if (users != null && !users.isEmpty()) {
                    view.showUsers(new ArrayList<>(users));
                }
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                if (!(e instanceof NullPointerException)) {
                    view.showMessage("Error happened while getting list of all users");
                }
            }

            @Override
            public void onComplete() {
            }
        }, null);
    }

    @Override
    public void cancel() {
        view.hideLoading();
        interactor.cancel();
    }
}

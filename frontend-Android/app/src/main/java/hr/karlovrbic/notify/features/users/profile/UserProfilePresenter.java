package hr.karlovrbic.notify.features.users.profile;

import javax.inject.Inject;

import hr.karlovrbic.notify.model.User;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class UserProfilePresenter implements IUserProfile.Presenter {

    private IUserProfile.View view;
    private IUserProfile.Interactor interactor;

    @Inject
    public UserProfilePresenter(IUserProfile.View view, IUserProfile.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getUser(long userId) {
        view.showLoading("Loading profile…");
        interactor.execute(new DisposableObserver<User>() {
            @Override
            public void onNext(User user) {
                if (user != null) {
                    view.showUser(user);
                }
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.showMessage("Error happened while getting user");
            }

            @Override
            public void onComplete() {
            }
        }, userId);
    }

    @Override
    public void cancel() {
        view.hideLoading();
        interactor.cancel();
    }
}

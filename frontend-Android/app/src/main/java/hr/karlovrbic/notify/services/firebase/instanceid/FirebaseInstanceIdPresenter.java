package hr.karlovrbic.notify.services.firebase.instanceid;

import javax.inject.Inject;

import hr.karlovrbic.notify.model.TokenUpdate;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.services.firebase.instanceid.interactors.TokenUpdateInteractor;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class FirebaseInstanceIdPresenter implements IFirebaseInstanceId.Presenter {

    private static final String FIREBASE_TAG = "Firebase";

    private IFirebaseInstanceId.View view;
    private IFirebaseInstanceId.TokenUpdateInteractor tokenUpdateInteractor;

    @Inject
    public FirebaseInstanceIdPresenter(IFirebaseInstanceId.View view,
                                       IFirebaseInstanceId.TokenUpdateInteractor tokenUpdateInteractor) {
        this.view = view;
        this.tokenUpdateInteractor = tokenUpdateInteractor;
    }

    @Override
    public void updateToken(Long userId, String token) {
        if(userId == null && userId <= 0L) {
            view.log(FIREBASE_TAG, "User ID cannot be null or lower than 1. User is probably not logged in.");
        } else if (token == null || token.isEmpty()) {
            view.log(FIREBASE_TAG, "Token cannot be null");
        } else {
            TokenUpdateInteractor.Params params = new TokenUpdateInteractor.Params(userId, new TokenUpdate(token));
            tokenUpdateInteractor.execute(new DisposableObserver<User>() {
                @Override
                public void onNext(User user) {
                    view.log(FIREBASE_TAG, "Token updated");
                }

                @Override
                public void onError(Throwable e) {
                    view.log(FIREBASE_TAG, "Error happened while updating token");
                }

                @Override
                public void onComplete() {
                }
            }, params);
        }
    }
}

package hr.karlovrbic.notify.features.users.profile.interactors;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.features.users.profile.IUserProfile;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class UserInteractor extends BaseInteractor<User, Long> implements IUserProfile.Interactor {

    private ApiService service;

    @Inject
    public UserInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<User> createObservable(Long userId) {
        return service.getUser(userId);
    }
}

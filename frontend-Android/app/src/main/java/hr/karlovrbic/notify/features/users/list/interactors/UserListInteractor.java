package hr.karlovrbic.notify.features.users.list.interactors;

import java.util.List;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.features.users.list.IUserList;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class UserListInteractor extends BaseInteractor<List<User>, Void> implements IUserList.Interactor {

    private ApiService service;

    @Inject
    public UserListInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<List<User>> createObservable(Void aVoid) {
        return service.getAllUsers();
    }
}

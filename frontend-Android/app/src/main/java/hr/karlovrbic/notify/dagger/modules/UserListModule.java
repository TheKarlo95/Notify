package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.features.users.list.IUserList;
import hr.karlovrbic.notify.features.users.list.UserListPresenter;
import hr.karlovrbic.notify.features.users.list.interactors.UserListInteractor;

/**
 * Created by thekarlo95 on 21.01.17..
 */
@Module
public class UserListModule {

    private IUserList.View view;

    public UserListModule(IUserList.View view) {
        this.view = view;
    }

    @Provides
    public IUserList.View provideView() {
        return view;
    }

    @Provides
    public IUserList.Presenter providePresenter(UserListPresenter presenter) {
        return presenter;
    }

    @Provides
    public IUserList.Interactor provideInteractor(UserListInteractor interactor) {
        return interactor;
    }
}

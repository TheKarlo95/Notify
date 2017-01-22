package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.features.users.profile.IUserProfile;
import hr.karlovrbic.notify.features.users.profile.UserProfilePresenter;
import hr.karlovrbic.notify.features.users.profile.interactors.UserInteractor;

/**
 * Created by thekarlo95 on 21.01.17..
 */
@Module
public class UserProfileModule {

    private IUserProfile.View view;

    public UserProfileModule(IUserProfile.View view) {
        this.view = view;
    }

    @Provides
    public IUserProfile.View provideView() {
        return view;
    }

    @Provides
    public IUserProfile.Presenter providePresenter(UserProfilePresenter presenter) {
        return presenter;
    }

    @Provides
    public IUserProfile.Interactor provideInteractor(UserInteractor interactor) {
        return interactor;
    }
}

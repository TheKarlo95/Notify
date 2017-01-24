package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.services.firebase.instanceid.FirebaseInstanceIdPresenter;
import hr.karlovrbic.notify.services.firebase.instanceid.IFirebaseInstanceId;
import hr.karlovrbic.notify.services.firebase.instanceid.interactors.TokenUpdateInteractor;

/**
 * Created by thekarlo95 on 22.01.17..
 */
@Module
public class FirebaseInstanceIdModule {

    private IFirebaseInstanceId.View view;

    public FirebaseInstanceIdModule(IFirebaseInstanceId.View view) {
        this.view = view;
    }

    @Provides
    public IFirebaseInstanceId.View provideView() {
        return view;
    }

    @Provides
    public IFirebaseInstanceId.Presenter providePresenter(FirebaseInstanceIdPresenter presenter) {
        return presenter;
    }

    @Provides
    public IFirebaseInstanceId.TokenUpdateInteractor provideInteractor(TokenUpdateInteractor interactor) {
        return interactor;
    }
}

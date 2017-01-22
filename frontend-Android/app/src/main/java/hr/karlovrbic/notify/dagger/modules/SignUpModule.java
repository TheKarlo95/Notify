package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.features.users.signup.ISignUp;
import hr.karlovrbic.notify.features.users.signup.SignUpPresenter;
import hr.karlovrbic.notify.features.users.signup.interactors.SignUpInteractor;

/**
 * Created by thekarlo95 on 21.01.17..
 */
@Module
public class SignUpModule {

    private ISignUp.View view;

    public SignUpModule(ISignUp.View view) {
        this.view = view;
    }

    @Provides
    public ISignUp.View provideView() {
        return view;
    }

    @Provides
    public ISignUp.Presenter providePresenter(SignUpPresenter presenter) {
        return presenter;
    }

    @Provides
    public ISignUp.Interactor provideInteractor(SignUpInteractor interactor) {
        return interactor;
    }
}

package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.SignUpModule;
import hr.karlovrbic.notify.features.users.signup.SignUpActivity;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {SignUpModule.class})
public interface SignUpComponent {
    void inject(SignUpActivity activity);
}
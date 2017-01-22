package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.LoginModule;
import hr.karlovrbic.notify.features.users.login.LoginActivity;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}

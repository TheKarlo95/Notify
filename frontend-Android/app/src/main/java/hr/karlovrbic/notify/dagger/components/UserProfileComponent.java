package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.UserProfileModule;
import hr.karlovrbic.notify.features.users.profile.UserProfileFragment;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {UserProfileModule.class})
public interface UserProfileComponent {
    void inject(UserProfileFragment fragment);
}

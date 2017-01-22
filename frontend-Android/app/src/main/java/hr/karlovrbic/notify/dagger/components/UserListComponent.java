package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.UserListModule;
import hr.karlovrbic.notify.features.users.list.UserListFragment;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {UserListModule.class})
public interface UserListComponent {
    void inject(UserListFragment fragment);
}

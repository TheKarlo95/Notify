package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.FollowedEventListModule;
import hr.karlovrbic.notify.features.events.followed.FollowedEventListFragment;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {FollowedEventListModule.class})
public interface FollowedEventListComponent {
    void inject(FollowedEventListFragment fragment);
}

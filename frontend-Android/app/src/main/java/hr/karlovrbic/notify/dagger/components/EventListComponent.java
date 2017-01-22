package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.EventListModule;
import hr.karlovrbic.notify.features.events.list.EventListFragment;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {EventListModule.class})
public interface EventListComponent {
    void inject(EventListFragment fragment);
}

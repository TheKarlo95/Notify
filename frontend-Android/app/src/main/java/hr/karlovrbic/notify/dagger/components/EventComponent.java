package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.EventModule;
import hr.karlovrbic.notify.features.events.event.EventFragment;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {EventModule.class})
public interface EventComponent {
    void inject(EventFragment fragment);
}

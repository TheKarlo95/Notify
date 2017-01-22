package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.MyEventsModule;
import hr.karlovrbic.notify.features.events.myevents.MyEventsFragment;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {MyEventsModule.class})
public interface MyEventsComponent {
    void inject(MyEventsFragment fragment);
}

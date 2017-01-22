package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.MainModule;
import hr.karlovrbic.notify.features.main.MainActivity;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}

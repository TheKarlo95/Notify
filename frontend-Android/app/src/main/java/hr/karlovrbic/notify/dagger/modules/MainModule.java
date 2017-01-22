package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.features.main.IMain;

/**
 * Created by thekarlo95 on 21.01.17..
 */
@Module
public class MainModule {

    private IMain.View view;

    public MainModule(IMain.View view) {
        this.view = view;
    }

    @Provides
    public IMain.View provideView() {
        return view;
    }
}

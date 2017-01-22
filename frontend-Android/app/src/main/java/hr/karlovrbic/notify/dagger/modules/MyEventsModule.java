package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.features.events.myevents.IMyEvents;
import hr.karlovrbic.notify.features.events.myevents.MyEventsPresenter;
import hr.karlovrbic.notify.features.events.myevents.interactors.CreateEventInteractor;
import hr.karlovrbic.notify.features.events.myevents.interactors.MyEventsInteractor;

/**
 * Created by thekarlo95 on 22.01.17..
 */
@Module
public class MyEventsModule {

    private IMyEvents.View view;

    public MyEventsModule(IMyEvents.View view) {
        this.view = view;
    }

    @Provides
    public IMyEvents.View provideView() {
        return view;
    }

    @Provides
    public IMyEvents.Presenter providePresenter(MyEventsPresenter presenter) {
        return presenter;
    }

    @Provides
    public IMyEvents.Interactor provideInteractor(MyEventsInteractor interactor) {
        return interactor;
    }

    @Provides
    public IMyEvents.CreateEventInteractor provideCreateEventInteractor(CreateEventInteractor interactor) {
        return interactor;
    }
}

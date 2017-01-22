package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.features.events.event.EventPresenter;
import hr.karlovrbic.notify.features.events.event.IEvent;
import hr.karlovrbic.notify.features.events.event.interactors.CreateMessageInteractor;
import hr.karlovrbic.notify.features.events.event.interactors.EventInteractor;

/**
 * Created by thekarlo95 on 21.01.17..
 */
@Module
public class EventModule {

    private IEvent.View view;

    public EventModule(IEvent.View view) {
        this.view = view;
    }

    @Provides
    public IEvent.View provideView() {
        return view;
    }

    @Provides
    public IEvent.Presenter providePresenter(EventPresenter presenter) {
        return presenter;
    }

    @Provides
    public IEvent.Interactor provideInteractor(EventInteractor interactor) {
        return interactor;
    }

    @Provides
    public IEvent.CreateMessageInteractor provideCreateMessageInteractor(CreateMessageInteractor interactor) {
        return interactor;
    }
}

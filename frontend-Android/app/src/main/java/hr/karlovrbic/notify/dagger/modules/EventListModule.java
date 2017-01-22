package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.features.events.list.EventListPresenter;
import hr.karlovrbic.notify.features.events.list.IEventList;
import hr.karlovrbic.notify.features.events.list.interactors.EventListInteractor;
import hr.karlovrbic.notify.features.events.shared.IEventsShared;
import hr.karlovrbic.notify.features.events.shared.interactors.FollowInteractor;
import hr.karlovrbic.notify.features.events.shared.interactors.UnfollowInteractor;

/**
 * Created by thekarlo95 on 21.01.17..
 */
@Module
public class EventListModule {

    private IEventList.View view;

    public EventListModule(IEventList.View view) {
        this.view = view;
    }

    @Provides
    public IEventList.View provideView() {
        return view;
    }

    @Provides
    public IEventList.Presenter providePresenter(EventListPresenter presenter) {
        return presenter;
    }

    @Provides
    public IEventList.Interactor provideInteractor(EventListInteractor interactor) {
        return interactor;
    }

    @Provides
    public IEventsShared.FollowInteractor provideFollowInteractor(FollowInteractor interactor) {
        return interactor;
    }

    @Provides
    public IEventsShared.UnfollowInteractor provideUnfollowInteractor(UnfollowInteractor interactor) {
        return interactor;
    }
}

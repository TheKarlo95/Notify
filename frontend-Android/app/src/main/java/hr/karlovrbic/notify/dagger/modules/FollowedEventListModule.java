package hr.karlovrbic.notify.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.karlovrbic.notify.features.events.followed.FollowedEventListPresenter;
import hr.karlovrbic.notify.features.events.followed.IFollowedEventList;
import hr.karlovrbic.notify.features.events.followed.interactors.FollowedEventListInteractor;
import hr.karlovrbic.notify.features.events.shared.IEventsShared;
import hr.karlovrbic.notify.features.events.shared.interactors.FollowInteractor;
import hr.karlovrbic.notify.features.events.shared.interactors.UnfollowInteractor;

/**
 * Created by thekarlo95 on 21.01.17..
 */
@Module
public class FollowedEventListModule {

    private IFollowedEventList.View view;

    public FollowedEventListModule(IFollowedEventList.View view) {
        this.view = view;
    }

    @Provides
    public IFollowedEventList.View provideView() {
        return view;
    }

    @Provides
    public IFollowedEventList.Presenter providePresenter(FollowedEventListPresenter presenter) {
        return presenter;
    }

    @Provides
    public IFollowedEventList.Interactor provideInteractor(FollowedEventListInteractor interactor) {
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

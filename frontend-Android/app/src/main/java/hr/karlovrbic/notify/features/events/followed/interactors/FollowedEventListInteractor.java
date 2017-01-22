package hr.karlovrbic.notify.features.events.followed.interactors;

import java.util.List;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.followed.IFollowedEventList;
import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class FollowedEventListInteractor extends BaseInteractor<List<Event>, Long> implements IFollowedEventList.Interactor {

    private ApiService service;

    @Inject
    public FollowedEventListInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<List<Event>> createObservable(Long userId) {
        return service.getFollowedEvents(userId);
    }
}

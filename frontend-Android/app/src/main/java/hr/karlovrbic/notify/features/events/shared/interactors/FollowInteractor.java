package hr.karlovrbic.notify.features.events.shared.interactors;

import java.util.List;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.shared.IEventsShared;
import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 22.01.17..
 */

public class FollowInteractor extends BaseInteractor<Event, List<Long>> implements IEventsShared.FollowInteractor {

    private ApiService service;

    @Inject
    public FollowInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<Event> createObservable(List<Long> ids) {
        return service.followEvent(ids.get(0), ids.get(1));
    }
}
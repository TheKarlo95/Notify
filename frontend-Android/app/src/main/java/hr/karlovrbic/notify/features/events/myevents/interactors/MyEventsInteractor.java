package hr.karlovrbic.notify.features.events.myevents.interactors;

import java.util.List;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.myevents.IMyEvents;
import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class MyEventsInteractor extends BaseInteractor<List<Event>, Long> implements IMyEvents.Interactor {

    private ApiService service;

    @Inject
    public MyEventsInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<List<Event>> createObservable(Long userId) {
        return service.getCreatedEvents(userId);
    }
}

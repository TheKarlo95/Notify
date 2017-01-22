package hr.karlovrbic.notify.features.events.event.interactors;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.event.IEvent;
import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class EventInteractor extends BaseInteractor<Event, Long> implements IEvent.Interactor {

    private ApiService service;

    @Inject
    public EventInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<Event> createObservable(Long userId) {
        return service.getEvent(userId);
    }
}

package hr.karlovrbic.notify.features.events.myevents.interactors;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.myevents.IMyEvents;
import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.model.EventCreate;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class CreateEventInteractor extends BaseInteractor<Event, EventCreate> implements IMyEvents.CreateEventInteractor {

    private ApiService service;

    @Inject
    public CreateEventInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<Event> createObservable(EventCreate eventCreate) {
        return service.createEvent(eventCreate);
    }
}

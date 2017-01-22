package hr.karlovrbic.notify.features.events.list.interactors;

import java.util.List;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.list.IEventList;
import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class EventListInteractor extends BaseInteractor<List<Event>, Void> implements IEventList.Interactor {

    private ApiService service;

    @Inject
    public EventListInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<List<Event>> createObservable(Void aVoid) {
        return service.getAllEvents();
    }
}

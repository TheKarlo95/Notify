package hr.karlovrbic.notify.features.events.shared.interactors;

import android.annotation.SuppressLint;

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

public class UnfollowInteractor extends BaseInteractor<Event, List<Long>> implements IEventsShared.UnfollowInteractor {

    private ApiService service;

    @Inject
    public UnfollowInteractor(ApiService service) {
        this.service = service;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected Observable<Event> createObservable(List<Long> ids) {
        Long userId = ids.get(0);
        Long eventId = ids.get(1);

        return service.unfollowEvent(userId, eventId);
    }
}

package hr.karlovrbic.notify.features.events.shared.interactors;

import android.annotation.SuppressLint;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.shared.IEventsShared;
import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

import static hr.karlovrbic.notify.features.events.shared.IEventsShared.TOPIC_NAME_FORMAT;

/**
 * Created by thekarlo95 on 22.01.17..
 */

public class FollowInteractor extends BaseInteractor<Event, List<Long>> implements IEventsShared.FollowInteractor {

    private ApiService service;

    @Inject
    public FollowInteractor(ApiService service) {
        this.service = service;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected Observable<Event> createObservable(List<Long> ids) {
        Long userId = ids.get(0);
        Long eventId = ids.get(1);

        FirebaseMessaging.getInstance().subscribeToTopic(String.format(TOPIC_NAME_FORMAT, userId, eventId));

        return service.followEvent(userId, eventId);
    }
}

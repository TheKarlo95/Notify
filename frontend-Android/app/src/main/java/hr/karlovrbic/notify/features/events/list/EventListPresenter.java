package hr.karlovrbic.notify.features.events.list;

import android.annotation.SuppressLint;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.shared.IEventsShared;
import hr.karlovrbic.notify.model.Event;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class EventListPresenter implements IEventList.Presenter {

    private IEventList.View view;
    private IEventList.Interactor interactor;
    private IEventsShared.FollowInteractor followInteractor;
    private IEventsShared.UnfollowInteractor unfollowInteractor;

    @Inject
    public EventListPresenter(IEventList.View view,
                              IEventList.Interactor interactor,
                              IEventsShared.FollowInteractor followInteractor,
                              IEventsShared.UnfollowInteractor unfollowInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.followInteractor = followInteractor;
        this.unfollowInteractor = unfollowInteractor;
    }

    @Override
    public void getEventList() {
        view.showLoading("Loading event list…");
        interactor.execute(new DisposableObserver<List<Event>>() {
            @Override
            public void onNext(List<Event> events) {
                if (events != null && !events.isEmpty()) {
                    view.showEvents(new ArrayList<>(events));
                }
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                if (!(e instanceof NullPointerException)) {
                    view.showMessage("Error happened while getting list of all events");
                }
            }

            @Override
            public void onComplete() {
            }
        }, null);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void followClicked(Event event, long userId, final int adapterPosition) {
        if (!event.getCreator().getId().equals(userId)) {
            List<Long> ids = new ArrayList<>(2);
            ids.add(userId);
            ids.add(event.getId());

            view.showLoading("Loading");
            if (event.isFollower(userId)) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(String.format(IEventsShared.TOPIC_NAME_FORMAT
                        , event.getCreator().getId()
                        , event.getId()));
                unfollowInteractor.execute(new DisposableObserver<Event>() {
                    @Override
                    public void onNext(Event value) {
                        view.hideLoading();
                        view.changeEvent(value, adapterPosition);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideLoading();
                        view.showMessage("Error happened while getting trying to unfollow");
                    }

                    @Override
                    public void onComplete() {
                    }
                }, ids);
            } else {
                FirebaseMessaging.getInstance().subscribeToTopic(String.format(IEventsShared.TOPIC_NAME_FORMAT
                        , event.getCreator().getId()
                        , event.getId()));
                followInteractor.execute(new DisposableObserver<Event>() {
                    @Override
                    public void onNext(Event value) {
                        view.hideLoading();
                        view.changeEvent(value, adapterPosition);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideLoading();
                        view.showMessage("Error happened while getting trying to unfollow");
                    }

                    @Override
                    public void onComplete() {
                    }
                }, ids);
            }
        }
    }

    @Override
    public void cancel() {
        view.hideLoading();
        interactor.cancel();
    }
}

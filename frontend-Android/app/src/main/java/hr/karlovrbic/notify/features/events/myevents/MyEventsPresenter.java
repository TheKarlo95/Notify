package hr.karlovrbic.notify.features.events.myevents;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.model.EventCreate;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class MyEventsPresenter implements IMyEvents.Presenter {

    private IMyEvents.View view;
    private IMyEvents.Interactor interactor;
    private IMyEvents.CreateEventInteractor createEventInteractor;

    @Inject
    public MyEventsPresenter(IMyEvents.View view,
                             IMyEvents.Interactor interactor,
                             IMyEvents.CreateEventInteractor createEventInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.createEventInteractor = createEventInteractor;
    }

    @Override
    public void getMyEventList(long userId) {
        view.showLoading("Loading my events…");
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
                    view.showMessage("Error happened while getting list of yours events");
                }
            }

            @Override
            public void onComplete() {
            }
        }, userId);
    }

    @Override
    public void createEvent(EventCreate eventCreate) {
        view.showLoading("Creating event…");
        createEventInteractor.execute(new DisposableObserver<Event>() {
            @Override
            public void onNext(Event event) {
                view.hideLoading();
                view.addEvent(event);
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.showMessage("Error happened while creating event");
            }

            @Override
            public void onComplete() {

            }
        }, eventCreate);
    }

    @Override
    public void cancel() {
        view.hideLoading();
        interactor.cancel();
    }
}

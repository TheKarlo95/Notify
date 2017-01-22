package hr.karlovrbic.notify.features.events.event;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.event.interactors.CreateMessageInteractor;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.model.Message;
import hr.karlovrbic.notify.model.MessageCreate;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class EventPresenter implements IEvent.Presenter {

    private IEvent.View view;
    private IEvent.Interactor interactor;
    private IEvent.CreateMessageInteractor createMessageInteractor;

    @Inject
    public EventPresenter(IEvent.View view,
                          IEvent.Interactor interactor,
                          IEvent.CreateMessageInteractor createMessageInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.createMessageInteractor = createMessageInteractor;
    }

    @Override
    public void getEvent(long eventId) {
        view.showLoading("Loading event…");
        interactor.execute(new DisposableObserver<Event>() {
            @Override
            public void onNext(Event event) {
                if (event != null) {
                    view.showEvent(event);
                }
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.showMessage("Error happened while getting event");
            }

            @Override
            public void onComplete() {
            }
        }, eventId);
    }

    @Override
    public void createMessage(String message, Long eventId) {
        MessageCreate messageCreate = new MessageCreate(message);

        view.showLoading("Posting message…");
        createMessageInteractor.execute(new DisposableObserver<Message>() {
            @Override
            public void onNext(Message value) {
                if (value != null) {
                    view.addMessage(value);
                }
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.showMessage("Error happened while posting message");
            }

            @Override
            public void onComplete() {
            }
        }, new CreateMessageInteractor.Params(messageCreate, eventId));
    }

    @Override
    public void cancel() {
        view.hideLoading();
        interactor.cancel();
    }
}

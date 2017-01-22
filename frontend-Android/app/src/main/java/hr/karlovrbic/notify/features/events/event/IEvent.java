package hr.karlovrbic.notify.features.events.event;

import hr.karlovrbic.notify.features.shared.IBase;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.model.Message;

/**
 * Created by thekarlo95 on 22.01.17..
 */

public interface IEvent extends IBase {

    interface View extends IBase.View {
        void showEvent(Event event);

        void addMessage(Message value);
    }

    interface Presenter extends IBase.Presenter {
        void getEvent(long eventId);

        void createMessage(String message, Long eventId);
    }

    interface Interactor extends IBase.Interactor<Event, Long> {
    }

    interface CreateMessageInteractor extends IBase.Interactor<Message, hr.karlovrbic.notify.features.events.event.interactors.CreateMessageInteractor.Params> {
    }
}

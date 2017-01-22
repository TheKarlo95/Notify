package hr.karlovrbic.notify.features.events.myevents;

import java.util.ArrayList;
import java.util.List;

import hr.karlovrbic.notify.features.shared.IBase;
import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.model.EventCreate;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public interface IMyEvents extends IBase {

    interface View extends IBase.View {
        void showEvents(ArrayList<Event> users);

        void addEvent(Event event);

        void clear();
    }

    interface Presenter extends IBase.Presenter {
        void getMyEventList(long userId);

        void createEvent(EventCreate eventCreate);
    }

    interface Interactor extends IBase.Interactor<List<Event>, Long> {
    }

    interface CreateEventInteractor extends IBase.Interactor<Event, EventCreate> {
    }
}

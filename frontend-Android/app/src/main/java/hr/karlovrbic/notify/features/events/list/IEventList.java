package hr.karlovrbic.notify.features.events.list;

import java.util.ArrayList;
import java.util.List;

import hr.karlovrbic.notify.features.events.shared.IEventsShared;
import hr.karlovrbic.notify.features.shared.IBase;
import hr.karlovrbic.notify.model.Event;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public interface IEventList extends IEventsShared {

    interface View extends IEventsShared.View {
        void showEvents(ArrayList<Event> users);

        void clear();
    }

    interface Presenter extends IEventsShared.Presenter {
        void getEventList();
    }

    interface Interactor extends IBase.Interactor<List<Event>, Void> {
    }
}

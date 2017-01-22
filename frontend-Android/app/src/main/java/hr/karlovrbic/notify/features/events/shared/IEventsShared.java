package hr.karlovrbic.notify.features.events.shared;

import java.util.List;

import hr.karlovrbic.notify.features.shared.IBase;
import hr.karlovrbic.notify.model.Event;

/**
 * Created by thekarlo95 on 22.01.17..
 */

public interface IEventsShared extends IBase {

    interface View extends IBase.View {
        void changeEvent(Event event, int adapterPosition);
    }

    interface Presenter extends IBase.Presenter {
        void followClicked(Event event, long userId, int adapterPosition);
    }

    interface FollowInteractor extends IBase.Interactor<Event, List<Long>> {
    }

    interface UnfollowInteractor extends IBase.Interactor<Event, List<Long>> {
    }
}

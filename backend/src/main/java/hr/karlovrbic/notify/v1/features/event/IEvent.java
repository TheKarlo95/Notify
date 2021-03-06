package hr.karlovrbic.notify.v1.features.event;

import com.sun.istack.internal.NotNull;
import hr.karlovrbic.notify.v1.features.event.requests.EventCreateRequest;
import hr.karlovrbic.notify.v1.features.shared.IBase;
import hr.karlovrbic.notify.v1.model.json.EventJson;

import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IEvent {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        EventJson createEvent(@NotNull EventCreateRequest request);

        List<EventJson> getAllEvents();

        EventJson getEventById(@NotNull Long id);
    }

    interface CreateInteractor extends IBase.Interactor {
        EventJson create(@NotNull EventCreateRequest request);
    }

    interface GetAllInteractor extends IBase.Interactor {
        List<EventJson> getAll();
    }

    interface GetByIdInteractor extends IBase.Interactor {
        EventJson get(@NotNull Long id);
    }
}

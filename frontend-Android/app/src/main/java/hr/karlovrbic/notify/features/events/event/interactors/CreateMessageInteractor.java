package hr.karlovrbic.notify.features.events.event.interactors;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.events.event.IEvent;
import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.model.Message;
import hr.karlovrbic.notify.model.MessageCreate;
import hr.karlovrbic.notify.network.ApiService;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public class CreateMessageInteractor extends BaseInteractor<Message, CreateMessageInteractor.Params> implements IEvent.CreateMessageInteractor {

    private ApiService service;

    @Inject
    public CreateMessageInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<Message> createObservable(Params params) {
        return service.createMessage(params.getEventId(), params.getMessageCreate());
    }

    public static class Params {

        private MessageCreate messageCreate;
        private Long eventId;

        public Params(MessageCreate messageCreate, Long eventId) {
            this.messageCreate = messageCreate;
            this.eventId = eventId;
        }

        public Long getEventId() {
            return eventId;
        }

        public MessageCreate getMessageCreate() {
            return messageCreate;
        }
    }
}

package hr.karlovrbic.notify.v1.features.message.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.model.entity.Message;
import hr.karlovrbic.notify.v1.model.json.MessageJson;

import javax.validation.constraints.NotNull;

import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class MessageByIdInteractor implements IMessage.GetByIdInteractor {

    @Override
    public MessageJson get(@NotNull Long id) {
        Message message = JPAEMProvider.getEntityManager().find(Message.class, id);

        MessageJson messageJson = null;
        if (messages != null) {
            messageJson = message.toJson();
        }
        JPAEMProvider.close();

        return messageJson;
    }
}

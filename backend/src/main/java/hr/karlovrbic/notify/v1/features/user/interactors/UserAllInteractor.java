package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.json.UserJson;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class UserAllInteractor implements IUser.GetAllInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public List<UserJson> getAll() {
        List<User> users = JPAEMProvider.getEntityManager().createNamedQuery("User.selectAll").getResultList();
        JPAEMProvider.close();

        List<UserJson> userJsons = null;
        if (users != null && !users.isEmpty()) {
            userJsons = users.stream()
                    .map(User::toJson)
                    .collect(Collectors.toList());
        }

        return userJsons;
    }
}

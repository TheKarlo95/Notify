package hr.karlovrbic.notify.services.firebase.instanceid;

import hr.karlovrbic.notify.features.shared.IBase;
import hr.karlovrbic.notify.model.User;

/**
 * Created by thekarlo95 on 24.01.17..
 */

public interface IFirebaseInstanceId {

    interface View{
        void log(String tag, String message);
    }

    interface Presenter {
        void updateToken(Long userId, String token);
    }

    interface TokenUpdateInteractor extends IBase.Interactor<User, hr.karlovrbic.notify.services.firebase.instanceid.interactors.TokenUpdateInteractor.Params> {
    }
}

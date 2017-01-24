package hr.karlovrbic.notify.services.firebase.instanceid.interactors;

import javax.inject.Inject;

import hr.karlovrbic.notify.features.shared.interactors.BaseInteractor;
import hr.karlovrbic.notify.model.TokenUpdate;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.network.ApiService;
import hr.karlovrbic.notify.services.firebase.instanceid.IFirebaseInstanceId;
import io.reactivex.Observable;

/**
 * Created by thekarlo95 on 24.01.17..
 */

public class TokenUpdateInteractor extends BaseInteractor<User, TokenUpdateInteractor.Params>
        implements IFirebaseInstanceId.TokenUpdateInteractor {

    private ApiService service;

    @Inject
    public TokenUpdateInteractor(ApiService service) {
        this.service = service;
    }

    @Override
    protected Observable<User> createObservable(Params params) {
        return service.updateToken(params.getUserId(), params.getTokenUpdate());
    }

    public static class Params {

        private Long userId;
        private TokenUpdate tokenUpdate;

        public Params(Long userId, TokenUpdate tokenUpdate) {
            this.userId = userId;
            this.tokenUpdate = tokenUpdate;
        }

        public Long getUserId() {
            return userId;
        }

        public TokenUpdate getTokenUpdate() {
            return tokenUpdate;
        }
    }
}

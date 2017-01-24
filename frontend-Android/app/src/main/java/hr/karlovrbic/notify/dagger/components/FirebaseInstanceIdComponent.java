package hr.karlovrbic.notify.dagger.components;

import dagger.Subcomponent;
import hr.karlovrbic.notify.dagger.modules.FirebaseInstanceIdModule;
import hr.karlovrbic.notify.services.firebase.instanceid.MyFirebaseInstanceIdService;

/**
 * Created by thekarlo95 on 21.01.17..
 */

@Subcomponent(modules = {FirebaseInstanceIdModule.class})
public interface FirebaseInstanceIdComponent {
    void inject(MyFirebaseInstanceIdService activity);
}
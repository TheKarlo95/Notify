package hr.karlovrbic.notify.features.events.myevents.dialog;

import hr.karlovrbic.notify.model.EventCreate;

/**
 * Created by thekarlo95 on 22.01.17..
 */

public interface CreateEventDialogCallback {

    void callback(EventCreate eventCreate);
}

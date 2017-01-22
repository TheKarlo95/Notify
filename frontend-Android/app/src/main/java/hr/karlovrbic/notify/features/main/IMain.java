package hr.karlovrbic.notify.features.main;

import android.support.v4.app.Fragment;

import hr.karlovrbic.notify.features.shared.IBase;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public interface IMain extends IBase {

    interface View extends IBase.View {
        void addFragment(Fragment fragment);

        void replaceFragment(Fragment fragment);
    }
}

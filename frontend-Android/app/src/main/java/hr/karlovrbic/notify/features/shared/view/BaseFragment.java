package hr.karlovrbic.notify.features.shared.view;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import hr.karlovrbic.notify.AndroidApplication;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.features.shared.IBase;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public abstract class BaseFragment extends Fragment implements IBase.View {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies(((AndroidApplication) context.getApplicationContext()).getApplicationComponent());
    }

    @Override
    public void showLoading(String message) {
        FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseView) {
            ((BaseView) activity).showLoading(message);
        }
    }

    @Override
    public void showLoading(@StringRes int id) {
        FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseView) {
            ((BaseView) activity).showLoading(id);
        }
    }

    @Override
    public void hideLoading() {
        FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseView) {
            ((BaseView) activity).hideLoading();
        }
    }

    @Override
    public void showMessage(String message) {
        FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseView) {
            ((BaseView) activity).showMessage(message);
        }
    }

    @Override
    public void showMessage(@StringRes int id) {
        FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseView) {
            ((BaseView) activity).showMessage(id);
        }
    }

    protected abstract void injectDependencies(AppComponent appComponent);
}

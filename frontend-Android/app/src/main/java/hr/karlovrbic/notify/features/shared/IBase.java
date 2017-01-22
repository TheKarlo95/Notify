package hr.karlovrbic.notify.features.shared;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public interface IBase {

    interface View {
        void showLoading(String message);

        void showLoading(@StringRes int id);

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int id);
    }

    interface Presenter {
        void cancel();
    }

    interface Interactor<T, Params> {
        void execute(@NonNull DisposableObserver<T> observer, Params params);

        void cancel();
    }
}

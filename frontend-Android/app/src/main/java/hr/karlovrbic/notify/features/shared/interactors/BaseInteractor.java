package hr.karlovrbic.notify.features.shared.interactors;

import android.support.annotation.NonNull;

import hr.karlovrbic.notify.features.shared.IBase;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public abstract class BaseInteractor<T, Params> implements IBase.Interactor<T, Params> {

    private final CompositeDisposable disposables;

    public BaseInteractor() {
        this.disposables = new CompositeDisposable();
    }

    protected abstract Observable<T> createObservable(Params params);

    @Override
    public void execute(@NonNull DisposableObserver<T> observer, Params params) {
        final Observable<T> observable = this.createObservable(params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        addDisposable(observable.subscribeWith(observer));
    }

    @Override
    public void cancel() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(@NonNull Disposable disposable) {
        disposables.add(disposable);
    }

}

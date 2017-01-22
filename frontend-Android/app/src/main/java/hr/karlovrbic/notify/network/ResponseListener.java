package hr.karlovrbic.notify.network;

public interface ResponseListener<T> {

    void onSuccess(T result);

    void onError(String message);
}
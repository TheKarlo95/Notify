package hr.karlovrbic.notify.network;

import java.util.List;

import hr.karlovrbic.notify.model.Event;
import hr.karlovrbic.notify.model.EventCreate;
import hr.karlovrbic.notify.model.Message;
import hr.karlovrbic.notify.model.MessageCreate;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.model.UserLogin;
import hr.karlovrbic.notify.model.UserSignUp;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by TheKarlo95 on 18.10.2016..
 */
public interface ApiService {

    @POST("users/login")
    Observable<User> login(@Body UserLogin userLogin);

    @POST("users")
    Observable<User> signUp(@Body UserSignUp userSignUp);

    @GET("users")
    Observable<List<User>> getAllUsers();

    @GET("users/{id}")
    Observable<User> getUser(@Path("id") Long userId);


    @POST("events")
    Observable<Event> createEvent(@Body EventCreate eventCreate);

    @GET("events")
    Observable<List<Event>> getAllEvents();

    @GET("events/{id}")
    Observable<Event> getEvent(@Path("id") Long eventId);

    @GET("users/{id}/events")
    Observable<List<Event>> getCreatedEvents(@Path("id") Long userId);

    @GET("users/{id}/followed")
    Observable<List<Event>> getFollowedEvents(@Path("id") Long userId);

    @PATCH("users/{userId}/follow/{eventId}")
    Observable<Event> followEvent(@Path("userId") Long userId, @Path("eventId") Long eventId);

    @PATCH("users/{userId}/unfollow/{eventId}")
    Observable<Event> unfollowEvent(@Path("userId") Long userId, @Path("eventId") Long eventId);

    @POST("events/{eventId}/messages")
    Observable<Message> createMessage(@Path("eventId") Long eventId, @Body MessageCreate messageCreate);
}

package vivenkko.trailquest.retrofit;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vivenkko.trailquest.models.Event;
import vivenkko.trailquest.models.Trail;
import vivenkko.trailquest.models.User;

/**
 * Created by magomez on 20/03/2018.
 */

public interface TrailQuestController {

    // PETICIONES DE USUARIOS
    @POST("/auth/login")
    Call<User> login(@Body User logginUser);

    @Multipart
    @POST("/auth/register")
    Call<User> signUp(@Part MultipartBody.Part photo, @Part("displayName") RequestBody displayName,
                      @Part("email") RequestBody email, @Part("password") RequestBody password);

    @GET("/auth/read/{id}")
    Call<User> findUserById(@Header("Authorization") String token, @Path("id") String user_id);

    @Multipart
    @PUT("/auth/update/{id}")
    Call<User> editUser(@Part("displayName") RequestBody displayName, @Part("email") RequestBody email , @Part MultipartBody.Part photo,
                        @Part("password") RequestBody password, @Path("id") String user_id);

    @DELETE("/auth/delete/{id}")
    Call<User> deleteUser(@Header("Authorization") String token, @Path("id") String user_id);


    //PETICIONES DE RUTAS
    @GET("/trails/list")
    Call<List<Trail>> listTrails(@Header("Authorization") String token);

    @GET("/trails/filterMine")
    Call<List<Trail>> listMyTrails(@Header("Authorization") String token);

    @POST("/trails/filterByDifficulty")
    Call<List<Trail>> filterByDifficulty(@Header("Authorization") String token, @Body Trail difficultyTrail);

    @GET("/trails/ranking")
    Call<List<Trail>> ranking(@Header("Authorization") String token);

    @GET("/trails/favorites")
    Call<List<Trail>> favorites(@Header("Authorization") String token);

    @POST("/trails/makeFav/{id}")
    Call<Trail> makeFavorite(@Header("Authorization") String token, @Body Trail favoriteTrail, @Path("id") String trail_id);

    @DELETE("/trails/removeFav/{id}")
    Call<Trail> removeFavorite(@Header("Authorization") String token, @Body Trail unfavoriteTrail, @Path("id") String trail_id);

    @Multipart
    @POST("/trails/create")
    Call<Trail> addTrail(@Header("Authorization") String token,
                         @Part("title") RequestBody title, @Part("description") RequestBody description , @Part MultipartBody.Part photo,
                         @Part("rate") RequestBody rate, @Part("city") RequestBody city , @Part("location") RequestBody location,
                         @Part("distance") RequestBody distance, @Part("difficulty") RequestBody difficulty);

    @GET("/trails/read/{id}")
    Call<Trail> findTrailById(@Header("Authorization") String token, @Path("id") String trail_id);

    @Multipart
    @PUT("/trails/update/{id}")
    Call<Trail> editTrail(@Header("Authorization") String token,
                          @Part("title") RequestBody title, @Part("description") RequestBody description , @Part MultipartBody.Part photo,
                          @Part("rate") RequestBody rate, @Part("city") RequestBody city , @Part("location") RequestBody location,
                          @Part("distance") RequestBody distance, @Part("difficulty") RequestBody difficulty);

    @DELETE("/trails/delete/{id}")
    Call<Trail> deleteTrail(@Header("Authorization") String token, @Path("id") String trail_id);


    // PETICIONES DE EVENTOS
    @GET("/events/list}")
    Call<List<Event>> listEvents(@Header("Authorization") String token);

    @GET("/events/following}")
    Call<List<Event>> listFollowing(@Header("Authorization") String token);

    @POST("/events/listByCity")
    Call<List<Event>> listByCity(@Header("Authorization") String token, @Body Event cityTrail);

    @POST("/events/follow/{id}")
    Call<Event> follow(@Header("Authorization") String token, @Body Event followTrail, @Path("id") String event_id);

    @DELETE("/events/unfollow/{id}")
    Call<Event> unfollow(@Header("Authorization") String token, @Body Event unfollowTrail, @Path("id") String event_id);

    @Multipart
    @POST("/events/create")
    Call<ResponseBody> addEvent(@Header("Authorization") String token,
                                @Part("title") RequestBody title, @Part("description") RequestBody description , @Part MultipartBody.Part photo,
                                @Part("city") RequestBody city , @Part("location") RequestBody location,
                                @Part("country") RequestBody country, @Part("date") RequestBody date);

    @GET("/events/read/{id}")
    Call<Event> findEventById(@Header("Authorization") String token, @Path("id") String event_id);

    @Multipart
    @PUT("/events/update/{id}")
    Call<Event> editEvent(@Header("Authorization") String token,
                          @Part("title") RequestBody title, @Part("description") RequestBody description , @Part MultipartBody.Part photo,
                          @Part("city") RequestBody city , @Part("location") RequestBody location,
                          @Part("country") RequestBody country, @Part("date") RequestBody date, @Path("id") String event_id);

    @DELETE("/events/delete/{id}")
    Call<Event> deleteEvent(@Header("Authorization") String token, @Path("id") String event_id);

}

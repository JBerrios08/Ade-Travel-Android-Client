package com.adetravel.api;

import com.adetravel.models.AuthResponse;
import com.adetravel.models.Destination;
import com.adetravel.models.PackageDetail;
import com.adetravel.models.PackageSummary;
import com.adetravel.models.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/destinos/")
    Call<List<Destination>> getDestinations();

    @GET("api/paquetes/")
    Call<List<PackageSummary>> getPackages();

    @GET("api/paquetes/{id}/")
    Call<PackageDetail> getPackageDetail(@Path("id") int id);

    @POST("api/auth/login/")
    Call<AuthResponse> login(@Body java.util.Map<String, String> body);

    @POST("api/auth/register/")
    Call<AuthResponse> register(@Body java.util.Map<String, String> body);

    @POST("api/reservas/")
    Call<Reservation> createReservation(@Header("Authorization") String token, @Body java.util.Map<String, Object> body);

    @GET("api/mis_reservas/")
    Call<List<Reservation>> getMyReservations(@Header("Authorization") String token);
}

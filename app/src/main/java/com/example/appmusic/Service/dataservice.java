package com.example.appmusic.Service;

import java.util.List;

import com.example.appmusic.model.albumModel;
import com.example.appmusic.model.baihatModel;
import com.example.appmusic.model.chudevatheloai;
import com.example.appmusic.model.playlistModel;
import com.example.appmusic.model.quangcaoModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface dataservice {

    @GET("quangcao.php")
    Call<List<quangcaoModel>> getdataquangcao() ;

    @GET("playlist.php")
    Call<List<playlistModel>> getdataplaylist() ;


    @GET("theloaivachude.php")
    Call<chudevatheloai> getchudevatheloai() ;


    @GET("abum.php")
    Call<List<albumModel>> getalbum() ;

    @GET("laydanhsachcao.php")
    Call<List<baihatModel>> getbaihat() ;

    @FormUrlEncoded
    @POST("danhsah.php")
    Call<List<baihatModel>> getbaihattheoquangcao(@Field("idquangcao") String idquangcao ) ;

    @FormUrlEncoded
    @POST("danhsah.php")
    Call<List<baihatModel>> getplaylisttheobaihat(@Field("idplaylist") String idplaylist ) ;

    @FormUrlEncoded
    @POST("danhsah.php")
    Call<List<baihatModel>> getbaihattheotheloai (@Field("idtheloai") String idtheloai ) ;

    @FormUrlEncoded
    @POST("danhsah.php")
    Call<List<baihatModel>> getbaihattheoalbum (@Field("idalbum") String idalbum ) ;

    @FormUrlEncoded
    @POST("luotthich.php")
    Call<String> getlthemluotthich (@Field("luotthich") String luotthich , @Field("idbaihat") String idbaihat ) ;

    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<baihatModel>> gettimkiembaihat (@Field("tukhoa") String tukhoa ) ;
}

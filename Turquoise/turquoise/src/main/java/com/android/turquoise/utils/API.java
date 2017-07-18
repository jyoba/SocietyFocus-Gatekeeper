package com.android.turquoise.utils;


import com.android.turquoise.model.request.UploadImage;
import com.android.turquoise.model.response.BasicResponse;
import com.android.turquoise.model.response.LoginResponse;
import com.android.turquoise.model.response.SocietyListResponse;
import com.android.turquoise.model.response.UploadImageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by jikoobaruah on 21/01/16.
 */
public interface API {

    //    String SERVER_URL = "http://192.168.11.150:8080";
//    String SERVER_URL = "http://10.0.2.2:8080/";
    String SERVER_URL = "http://societyfocus.com/";
    String API_PATH_PATTERN = "service/";


    @POST(API_PATH_PATTERN + "access/login")
    public Call<LoginResponse>
    login(@Header(ILoginHeaderParams.SOCIETY) String society,
          @Header(ILoginHeaderParams.USERNAME) String username,
          @Header(ILoginHeaderParams.PASSWORD) String password,
          @Header(ILoginHeaderParams.DEVICE_ID) String firbaseId,
          @Header(ILoginHeaderParams.DEVICE_IDOld) String deviceID);

    //
    @POST(API_PATH_PATTERN + "upload/image/base64")
    public Call<UploadImageResponse> uploadimage(@Body UploadImage uploadImage);

    @GET(API_PATH_PATTERN + "society")
    public Call<SocietyListResponse> getSocietyList();

    @GET(API_PATH_PATTERN + "access/updatefiretoken")
    public Call<BasicResponse> sendFcmToken(@Header(IPostLoginHeaderParams.AUTH_TOKEN) String authToken, @Query("firebasetoken") String firebasetoken);

    interface ILoginHeaderParams {
        String SOCIETY = "X-Society";
        String USERNAME = "X-Username";
        String PASSWORD = "X-Password";
        String DEVICE_ID = "X-DeviceID";
        String DEVICE_IDOld = "X-DeviceIDOld";
        String ACCESS_TOKEN = "X-AccessToken";
    }

    interface IPostLoginHeaderParams {
        String AUTH_TOKEN = "X-Auth-Token";

    }

    interface IAssetParams {
        String ID = "id";
    }

    interface IEventParams {
        String MONTH = "month";
        String YEAR = "year";
    }

//
//    @POST(API_PATH_PATTERN + "society/noticeboard/add")
//    public Call<AddNewNoticeResponse> addNewNotice(@Header(IPostLoginHeaderParams.AUTH_TOKEN) String authToken, @Body AddNewNotice newNotice);
}
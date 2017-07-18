package com.android.turquoise.model.response;


import com.android.turquoise.model.Society;
import com.android.turquoise.model.User;

/**
 * Created by jikoobaruah on 21/01/16.
 */
public class LoginResponse extends BaseResponse {

    public LoginBody body;

    public static class UserDetail {
        public User user;
    }

    public class LoginBody {
        //        public Date created;
        public String token;
        public UserDetail userDetails;
        public Society society;
    }

}
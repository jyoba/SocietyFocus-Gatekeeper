package com.android.turquoise.model.response;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by jikoobaruah on 22/01/16.
 */
public class BaseResponse {

    public static SimpleDateFormat API_SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");
    public static SimpleDateFormat API_SDF_2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    static {
        API_SDF.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public int status;
    public String message;
}
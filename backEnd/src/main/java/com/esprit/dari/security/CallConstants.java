package com.esprit.dari.security;

import com.twilio.Twilio;

public class CallConstants {
    public final static String SID_ACCOUNT = "ACf66bc3a0d82b4d663553494773220c30";
    public final static String AUTH_ID = "d0c4dd4f26e26fb889a0479b1b86fa8d";
    public final static String FROM_NUMBER="+16173792102";
    public final static String TO_NUMBER ="+21650713446";

    static {
        Twilio.init(SID_ACCOUNT, AUTH_ID);
    }

}

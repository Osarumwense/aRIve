package com.enghack.aRIve.service;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import java.util.*;
import com.twilio.sdk.*;
import com.twilio.sdk.resource.factory.*;
import com.twilio.sdk.resource.instance.*;
import com.twilio.sdk.resource.list.*;



public class SendService {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC4752affe9157fe7c89cb075e4f1e3487";
    public static final String AUTH_TOKEN ="62a0610a1370d1f0e0241beaca2985a1";

    public void sendSms() throws TwilioRestException{
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build the parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", "+14169387048"));
        params.add(new BasicNameValuePair("From", "+16475035071"));
        params.add(new BasicNameValuePair("Body", "Wake up!!!"));


        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        System.out.println(message.getSid());
    }
    public void sendCall() throws TwilioRestException {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build the parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", "+14169387048"));
        params.add(new BasicNameValuePair("From", "+16475035071"));

        CallFactory callFactory = client.getAccount().getCallFactory();
        Call call = callFactory.create(params);
        System.out.println(call.getSid());
    }


        public void receiveSms() throws TwilioRestException {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            Message message = client.getAccount().getMessage("SMdee9a369c2724c86a0231c0fa89a69c9");
            System.out.println(message.getBody());
        }
    }


package org.d2k.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.util.Log;

/**
 * 
 * @author catmeoww
 *
 */
public class HttpHelper {
	
	String CLASSTAG = HttpHelper.class.getSimpleName();
	/**
	 * Input the url string (notice! the url should include "http://") 
	 * the method will give you the response in String format
	 * @param whereUrl
	 * @return String 
	 */
	public String performGetToString(String whereUrl){
        HttpGet request = new HttpGet(whereUrl);
        try{
            Log.d(CLASSTAG,"performGetToString(): successfully get response");
            return requestToString(request);
        }catch(Exception ex){
        	Log.e(CLASSTAG,"performGetToString(): failed to get response!");
            return "Failed!";
        }
	}
	/**
	 * 
	 * @param whereUrl
	 * @param param
	 * @return
	 */
	public String performPostToString(String whereUrl, Map<String, String> param){
        HttpPost request = new HttpPost(whereUrl);
        
        // data - name/value params
        List<NameValuePair> nvps = null;
        if ((param != null) && (param.size() > 0)) {
           nvps = new ArrayList<NameValuePair>();
           for (String key : param.keySet()) {
              Log.d(CLASSTAG, "performPostToString(): adding param: " + key + " | " + param.get(key));
              nvps.add(new BasicNameValuePair(key, param.get(key)));
           }
        }
        if (nvps != null) {
           try {
        	   request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
           } catch (UnsupportedEncodingException e) {
              Log.e(CLASSTAG, "performPostToString(): failed to encode", e);
           }
        }
        
        //make request
        try{
            Log.d(CLASSTAG,"performGetToString(): successfully get response");
            return requestToString(request);
        }catch(Exception ex){
        	Log.e(CLASSTAG,"performGetToString(): failed to get response!");
            return "Failed!";
        }
	}

	public InputStream performGetToInputStream(String whereUrl){
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(whereUrl);
        try{
            HttpResponse response = client.execute(request);
            Log.d(CLASSTAG,"performGetToInputStream(): successfully get response");
            return response.getEntity().getContent();
        }catch(Exception ex){
        	Log.e(CLASSTAG,"performGetToInputStream(): failed to get response!");
            return null;
        }
	}
	
	/**
	 * send request for getToString and postToString
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
    public String requestToString(HttpRequestBase request) throws ClientProtocolException, IOException{
        String result = "";
        HttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(request);
        InputStream in = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder str = new StringBuilder();
        String line = null;
        try{
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        Log.d(CLASSTAG,"requestToString(): result is "+result);
        return result;
    }

}

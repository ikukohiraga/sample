/**
 * 
 */
package com.picnic.sample;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

/**
 * @author kkawagoi
 *
 */
public class searchAsyncTask extends AsyncTask<String, Integer, String>{
//  private OnGetItemLightListener mListener = null;
// private GetItemLightApi mApi = null;

   public OnApiListener mListener;
   public String mUrl;
   
   public searchAsyncTask(OnApiListener listener , String url) {

       this.mListener = listener;
       this.mUrl = url;
       //       mApi = new GetApi();
   }

 @Override
 protected String doInBackground(String... params) {

    
    		 
     DefaultHttpClient client = new DefaultHttpClient();
     HttpUriRequest method = new HttpGet(mUrl);
     HttpResponse response = null;
     
     String result = "";
     try {
         response = client.execute(method);
         result = EntityUtils.toString(response.getEntity());
     } catch (Exception e) {
         e.getStackTrace();
         return null;
     }
     return result;
  }

  @Override
  protected void onPostExecute(String result) {
         this.mListener.onSuccess(result);
     }
}


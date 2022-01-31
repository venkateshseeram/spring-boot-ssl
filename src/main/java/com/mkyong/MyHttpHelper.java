package com.mkyong;

import com.google.gson.Gson;
import com.mkyong.exception.CustomException;
import org.apache.http.Header;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Component
public class MyHttpHelper {


    @Autowired
    private AsyncHttpClient asyncHttpClient;

    @Autowired
    private Gson gson;



    public <R> CompletionStage<R> get(String url, Map<String, String> params,
                                     Map<String, String> headers,
                                     Class<R> responseClass){


        BoundRequestBuilder getRequest = asyncHttpClient.prepareGet(url);

        params.forEach((k,v)->getRequest.addQueryParam(k,v));
        headers.forEach((k,v)->getRequest.addHeader(k,v));


        return makeBoundrequest(getRequest).thenApply(response ->{

            if(response.getStatusCode()==200) {
                return gson.fromJson(response.getResponseBody(), responseClass);

            }

               throw new CustomException(response.getResponseBody(), response.getStatusCode());

                }



                ).exceptionally(throwable -> {
                    throwable.printStackTrace();
                    throw new RuntimeException(throwable);
        });
    }

    private  CompletionStage<Response> makeBoundrequest(BoundRequestBuilder request) {



        ListenableFuture<Response> future = request.execute(new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                return response;
            }

            @Override
            public void onThrowable(Throwable t) {
                throw new RuntimeException(t);
            }
        });

        return future.toCompletableFuture();

    }


}

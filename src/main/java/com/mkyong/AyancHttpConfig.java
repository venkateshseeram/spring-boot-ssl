package com.mkyong;


import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.tls.Certificates;
import okhttp3.tls.HandshakeCertificates;
import org.apache.commons.io.FileUtils;
import org.apache.http.ssl.SSLContextBuilder;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.netty.ssl.JsseSslEngineFactory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import okhttp3.HttpUrl.Builder;

@Configuration
public class AyancHttpConfig {




    @Value("${trust-store}")
    private Resource trustStore;

    Logger logger = LoggerFactory.getLogger(AsyncHttpClient.class);


    @Bean
    public AsyncHttpClient getAsyncHttpClient() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(trustStore.getURL(), "secret".toCharArray())
                .build();

        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(new DefaultAsyncHttpClientConfig
                .Builder()
                .setSslEngineFactory(new JsseSslEngineFactory(sslContext))
                .setReadTimeout(5000)
                .build());


        return asyncHttpClient;

    }
// thrust all;

    public OkHttpClient getOkHttpClient() throws NoSuchAlgorithmException, KeyManagementException {

        TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        TrustManager[] trustAllCerts = new TrustManager[]{trustManager};

        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManager);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(100);
        dispatcher.setMaxRequestsPerHost(100);

        builder.dispatcher(dispatcher);

        builder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {


                logger.info("reqiest received {}z", chain.request());


                return chain.proceed(chain.request());
            }
        });

        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;

    }
    //add required certificate

    @Bean
    public OkHttpClient okHttpClient() throws NoSuchAlgorithmException, KeyManagementException, IOException {


        File file = new File("src/main/resources/badssl-com.pem");
        String str = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        X509Certificate letsEncryptCertificateAuthority= Certificates.decodeCertificatePem(str);

        HandshakeCertificates certificates = new HandshakeCertificates.Builder()
                .addTrustedCertificate(letsEncryptCertificateAuthority)
                .build();

        return new OkHttpClient.Builder()
               .sslSocketFactory(certificates.sslSocketFactory(), certificates.trustManager())
                .build();

    }












}

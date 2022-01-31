package com.mkyong;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class CustomTrustManager implements X509TrustManager {

    TrustManagerFactory tmf = TrustManagerFactory
            .getInstance(TrustManagerFactory.getDefaultAlgorithm());

    X509TrustManager x509Tm = null;

    public CustomTrustManager() throws NoSuchAlgorithmException, KeyStoreException {
     // Using null here initialises the TMF with the default trust store.
        tmf.init((KeyStore) null);

        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

        try (FileOutputStream fos = new FileOutputStream("classpath:newKeyStoreFileName.jks")) {
            ks.store(fos, "chaneit".toCharArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        tmf.init(ks);

        // Get hold of the default trust manager
        for (TrustManager tm : tmf.getTrustManagers()) {
            if (tm instanceof X509TrustManager) {
                x509Tm = (X509TrustManager) tm;
                break;
            }
        }





    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

       x509Tm.checkClientTrusted(chain,authType);

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        x509Tm.checkServerTrusted(chain,authType);

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return x509Tm.getAcceptedIssuers();
    }
}

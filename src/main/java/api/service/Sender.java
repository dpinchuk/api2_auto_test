package api.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static api.utils.Constants.CHARSET;

/**
 * service class for (@link resources.api)
 */

public final class Sender {

    private String urlAddress;
    private String jsonString;
    private HttpPost request;
    private HttpResponse response;
    private StringEntity params;
    private String jsonAsString;

    public Sender(String urlAddress, String jsonString) {
        this.urlAddress = urlAddress;
        this.jsonString = jsonString;
    }

    /* The method executes an POST HTTP-request to the server and passes JSON as param.
     * Considered the possibility of lack of certification
     * UTF-8 encoding is used
     */
    public JsonObject sendApiRequest() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLContextBuilder builder = SSLContexts.custom();
        builder.loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
                return true;
            }
        });
        SSLContext sslContext = builder.build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext, new X509HostnameVerifier() {
            @Override
            public void verify(String host, SSLSocket ssl)
                    throws IOException {
            }

            @Override
            public void verify(String host, X509Certificate cert)
                    throws SSLException {
            }

            @Override
            public void verify(String host, String[] cns,
                               String[] subjectAlts) throws SSLException {
            }

            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("https", sslsf)
                .build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm).build();
        this.request = new HttpPost(this.urlAddress);
        this.params = new StringEntity(this.jsonString, CHARSET);
        this.request.addHeader("content-type", "application/json");
        this.request.addHeader("Accept", "application/json");
        this.request.setEntity(this.params);
        this.response = httpclient.execute(this.request);
        this.jsonAsString = EntityUtils.toString(this.response.getEntity(), "UTF-8");
        return new Gson().fromJson(this.jsonAsString, JsonObject.class);
    }

}
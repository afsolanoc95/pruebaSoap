package com.programa;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-01-14T15:57:19.003-05:00
 * Generated source version: 3.4.2
 *
 */
@WebServiceClient(name = "SOATestEndpointService",
                  wsdlLocation = "file:/D:/cosos/entrevista/guardarEmpleado/src/main/resources/META-INF/wsdl/test.wsdl",
                  targetNamespace = "http://programa.com")
public class SOATestEndpointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://programa.com", "SOATestEndpointService");
    public final static QName SOATestEndpoint = new QName("http://programa.com", "SOATestEndpoint");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/cosos/entrevista/guardarEmpleado/src/main/resources/META-INF/wsdl/test.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SOATestEndpointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/D:/cosos/entrevista/guardarEmpleado/src/main/resources/META-INF/wsdl/test.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SOATestEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SOATestEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SOATestEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public SOATestEndpointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public SOATestEndpointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public SOATestEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns SOATestEndpoint
     */
    @WebEndpoint(name = "SOATestEndpoint")
    public SOATestEndpoint getSOATestEndpoint() {
        return super.getPort(SOATestEndpoint, SOATestEndpoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SOATestEndpoint
     */
    @WebEndpoint(name = "SOATestEndpoint")
    public SOATestEndpoint getSOATestEndpoint(WebServiceFeature... features) {
        return super.getPort(SOATestEndpoint, SOATestEndpoint.class, features);
    }

}

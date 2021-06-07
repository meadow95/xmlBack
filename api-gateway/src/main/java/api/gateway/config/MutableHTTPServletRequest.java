package api.gateway.config;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MutableHTTPServletRequest extends HttpServletRequestWrapper {
	
	private final Map<String, String> customHeaders;
	
	
	public MutableHTTPServletRequest(HttpServletRequest request) {
		super(request);
		this.customHeaders = new HashMap<String, String>();
	}
    
    public void putHeader(String name, String value){
        this.customHeaders.put(name, value);
    }
 
    public String getHeader(String name) {
        // check the custom headers first
        String headerValue = customHeaders.get(name);
        
        if (headerValue != null){
            return headerValue;
        }
        // else return from into the original wrapped object
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }
    
    @Override
    public Enumeration<String> getHeaders(String name) {
        Set<String> set = new HashSet<>();
        Optional.ofNullable(customHeaders.get(name)).ifPresent(h -> set.add(h));
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaders(name);
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }
        Optional.ofNullable(customHeaders.get(name)).ifPresent(h -> set.add(h));
        return Collections.enumeration(set);
    }
    
    public Enumeration<String> getHeaderNames() {
        // create a set of the custom header names
        Set<String> set = new HashSet<String>(customHeaders.keySet());
        
        // now add the headers from the wrapped request object
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }
 
        // create an enumeration from the set and return
        return Collections.enumeration(set);
    }
}

package api.http;

import org.eclipse.jetty.server.Request;
import utils.http.rest.response.BaseRestResult;

public interface Handler {
    public abstract BaseRestResult execute(Request request);
}
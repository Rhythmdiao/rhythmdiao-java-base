package handlers;

import annotation.ClassInfo;
import annotation.MethodInfo;
import annotation.RestfulHandler;
import api.http.Handler;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import rest.result.BaseRestResult;
import rest.result.CustomStatusCode;
import rest.result.json.JsonRestResult;

import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMapWithExpectedSize;

@ClassInfo(author = "", description = "test handler", date = "2015.4.3")
public
@Controller
@RestfulHandler(uri = "/test", method = HttpMethods.GET, identification = "mytest")
class TestHandler implements Handler {
    private static final Logger LOG = LoggerFactory.getLogger(TestHandler.class);

    @MethodInfo(author = "", description = "execute method", date = "2015.4.3")
    public BaseRestResult execute(final Request request) {
        BaseRestResult result = new JsonRestResult();
        result.setStatusCode(CustomStatusCode.SUCCESS.getStatusCode());
        result.setMsg("for test");
        HashMap<String, String> hashMap = newHashMapWithExpectedSize(4);
        hashMap.put("text", "1");
        hashMap.put("num", "2");
        hashMap.put("text2", "3");
        hashMap.put("num2", "4");
        result.setResult(hashMap);
        return result;
    }
}

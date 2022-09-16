package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

// Reference https://www.cnblogs.com/suhaha/p/14641386.html
public class RoundRibbonHttpEndpointRouter implements HttpEndpointRouter {
    private int lastIndex = 0;
    @Override
    public String route(List<String> urls) {
        if (lastIndex == urls.size()-1) {
            lastIndex = 0;
        } else {
            lastIndex++;
        }
        return urls.get(lastIndex);
    }
}

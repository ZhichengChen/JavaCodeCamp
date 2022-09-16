package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class WeightHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        int randomInt = random.nextInt(100);
        int index = 0;
        // - server01,20
        // - server02,30
        // - server03,50
        if (randomInt >= 0 && randomInt < 20) {
            index = 0;
        } else if (randomInt >= 20 && randomInt < 50) {
            index = 1;
        } else if (randomInt >= 50) {
            index = 2;
        }

        return urls.get(index);
    }
}

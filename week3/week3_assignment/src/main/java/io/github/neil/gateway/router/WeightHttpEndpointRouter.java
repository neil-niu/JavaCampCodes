package io.github.neil.gateway.router;

import io.github.neil.gateway.client.netty4.NettyHttpClient;

import java.util.*;

public class WeightHttpEndpointRouter implements HttpEndpointRouter {

    private static Map<String,Integer> serviceWeightMap = new HashMap<String, Integer>();

    private static Integer pos = 0;
    static {
        serviceWeightMap.put("http://localhost:8801", 1);
        serviceWeightMap.put("http://localhost:8802", 3);
    }

    @Override
    public String route(List<String> urls) {

        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serviceWeightMap);

        //取得IP地址list
        Set<String> keySet = serverMap.keySet();
        Iterator<String> it = keySet.iterator();

        List<String> serverList = new ArrayList<String>();

        while (it.hasNext()) {
            String server = it.next();
            Integer weight = serverMap.get(server);
            for (int i=0; i<weight; i++) {
                serverList.add(server);
            }
        }

        String server = null;

        System.out.println("pos = " + pos);
        System.out.println("ServerList size = " + serverList.size());

        synchronized (pos) {
            if (pos >= serverList.size()) {
                pos = 0;
            }

            server = serverList.get(pos);
            pos++;
        }

        if (urls.contains(server)) {
            return server;
        } else {
            int size = urls.size();
            Random random = new Random(System.currentTimeMillis());
            return urls.get(random.nextInt(size));
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<String> mylist = new ArrayList<String>();
        mylist.add("http://localhost:8801");
        mylist.add("http://localhost:8802");
        WeightHttpEndpointRouter testRouter = new WeightHttpEndpointRouter();

        for (int i = 0; i < 100; i++) {
            System.out.println("rount.............");
            String getUrl = testRouter.route(mylist);
            System.out.println("result url : " + getUrl);
        }

    }
}

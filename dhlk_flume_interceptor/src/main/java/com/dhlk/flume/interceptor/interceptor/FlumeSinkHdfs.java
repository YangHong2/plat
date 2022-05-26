package com.dhlk.flume.interceptor.interceptor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * flume的数据sink到hdfs的时候,获取body中的的字段数据
 */
public class FlumeSinkHdfs implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {

        byte[] body = event.getBody();
        String jsonStr = new String(body);

        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(jsonStr);

        JsonElement after = obj.get("after");

        event.setBody(after.toString().getBytes());
       /* Set<Map.Entry<String, JsonElement>> entry = after.entrySet();
        Iterator<Map.Entry<String, JsonElement>> it = entry.iterator();
        String vs = "";


        while (it.hasNext()) {
            Map.Entry<String, JsonElement> elem = it.next();
            String val = elem.getValue().toString().replaceAll("\"","");

            vs += val + "\t";
            event.setBody(vs.getBytes());
        }*/
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }


    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new FlumeSinkHdfs();
        }

        @Override
        public void configure(Context context) {
        }
    }
}

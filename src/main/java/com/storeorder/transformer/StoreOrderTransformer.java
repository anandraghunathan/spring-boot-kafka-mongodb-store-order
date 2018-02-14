package com.storeorder.transformer;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StoreOrderTransformer {
    private static final Logger log = LoggerFactory.getLogger(StoreOrderTransformer.class);
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

    public String transform(String jsonOrder) throws JSONException {
        JSONObject xmlJSONObj = XML.toJSONObject(jsonOrder);
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        System.out.println("JSON STRING : " + jsonPrettyPrintString);

        return jsonPrettyPrintString;
    }
}

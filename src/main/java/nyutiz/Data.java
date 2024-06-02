package nyutiz;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Data {
    private Map<String, String> dataMap;

    public Data() {
        this.dataMap = new LinkedHashMap<>();
    }

    public void setData(String key, String value) {
        dataMap.put(key, value);
    }

    public String getData(String key) {
        return dataMap.get(key);
    }

    public String getDatas() {
        StringJoiner joiner = new StringJoiner("; ");
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            joiner.add(entry.getValue());
        }
        return joiner.toString();
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }
}

package com.agoda;

import java.util.ArrayList;
import java.util.HashMap;

public class Utils extends HashMap<String, ArrayList<Hotel>>  {
	@Override
    public ArrayList<Hotel> put(String key, ArrayList<Hotel> value) {
        return super.put(key.toLowerCase(), value);
    }

    public ArrayList<Hotel> get(String key) {
        return super.get(key.toLowerCase());
    }

    public boolean containsKey(String key) {
        return super.containsKey(key.toLowerCase());
    }


}

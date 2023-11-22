package ru.example.entity;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CounterCharsDAO {
    private InputStringEntity inputString;
    private final Map<Character, Integer> counterChar = new HashMap<>();
    public void clean(){
        counterChar.clear();
    }

    public Map<Character, Integer> addStringInMap(Character c){
        int count = 1;
        if(!counterChar.containsKey(c)){
            counterChar.put(c,count);
        }else{
            for(Map.Entry<Character, Integer> entry : counterChar.entrySet()){
                if(c == entry.getKey()){
                    Integer v = entry.getValue();
                    counterChar.replace(c, v, v+1);

                }
            }
        }
        return counterChar;
    }
    public Map<Character, Integer> getSortedMap(int index){
        Map<Character, Integer> sortedMap = new LinkedHashMap<Character, Integer>();


        for(;index>0;index--){
            for(Map.Entry<Character, Integer> entry : counterChar.entrySet()){
                if(index == entry.getValue()){
                    sortedMap.put(entry.getKey(),entry.getValue());

                }
            }
        }
        return sortedMap;
    }

}

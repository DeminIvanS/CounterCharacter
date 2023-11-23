package ru.example.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.model.InputStringEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CounterCharsImpl implements InputString {

    private final Map<Character, Integer> counterChar = new HashMap<>();

    public String getCountChars(InputStringEntity str) {
        Map<Character, Integer> sortedAndCounted;
        if (str.getString().length() > 255) {
            return "The line is too long. Maximum number of characters 255.";
        } else if (str.getString().isEmpty()) {
            return "The line is empty! Write something.";
        } else {
            char[] charArray = str.getString().toCharArray();
            clean();
            for (Character c : charArray) {

                addStringInMap(c);
            }
            sortedAndCounted = getSortedMap(charArray.length);
            getStringCountChars(sortedAndCounted).toString();
        }

        return getStringCountChars(sortedAndCounted).toString();
    }

    private String getStringCountChars(Map<Character, Integer> sorted) {
        StringBuilder builder = new StringBuilder();


        for (Map.Entry<Character, Integer> e : sorted.entrySet()) {
            builder.append("\"" + e.getKey() + "\": " + e.getValue() + ", ");
        }
        String answer = builder.delete(builder.length() - 2, builder.length() - 1).toString();
        builder.delete(0, builder.length());

        return answer;
    }

    public Map<Character, Integer> addStringInMap(Character c) {
        int count = 1;
        if (!counterChar.containsKey(c)) {
            counterChar.put(c, count);
        } else {
            for (Map.Entry<Character, Integer> entry : counterChar.entrySet()) {
                if (c == entry.getKey()) {
                    Integer v = entry.getValue();
                    counterChar.replace(c, v, v + 1);

                }
            }
        }
        return counterChar;
    }

    public Map<Character, Integer> getSortedMap(int index) {
        Map<Character, Integer> sortedMap = new LinkedHashMap<Character, Integer>();


        for (; index > 0; index--) {
            for (Map.Entry<Character, Integer> entry : counterChar.entrySet()) {
                if (index == entry.getValue()) {
                    sortedMap.put(entry.getKey(), entry.getValue());

                }
            }
        }
        return sortedMap;
    }

    public void clean() {
        counterChar.clear();
    }
}

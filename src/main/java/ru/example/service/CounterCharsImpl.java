package ru.example.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.entity.CounterCharsDAO;
import ru.example.entity.InputStringEntity;

import java.util.Map;
@Service
@AllArgsConstructor
public class CounterCharsImpl implements InputString {

    private final CounterCharsDAO counterCharsDAO;




    public String getCountChars(InputStringEntity str) {
        Map<Character, Integer> sortedAndCounted;
        if(str.getString().length()>255){
            return "The line is too long. Maximum number of characters 255.";
        } else if (str.getString().isEmpty()) {
            return "The line is empty! Write something.";
        }else {
            char[] charArray = str.getString().toCharArray();
            counterCharsDAO.clean();
            for (Character c : charArray) {

                counterCharsDAO.addStringInMap(c);
            }
            sortedAndCounted = counterCharsDAO.getSortedMap(charArray.length);
            getStringCountChars(sortedAndCounted).toString();
        }

        return getStringCountChars(sortedAndCounted).toString();
    }

    private String getStringCountChars(Map<Character, Integer> sorted){
        StringBuilder builder = new StringBuilder();


        for(Map.Entry<Character, Integer> e : sorted.entrySet()){
            builder.append("\"" + e.getKey() + "\": " + e.getValue() + ", ");
        }
        String answer = builder.delete(builder.length()-2,builder.length()-1).toString();
        builder.delete(0,builder.length());

       return answer;
    }


}

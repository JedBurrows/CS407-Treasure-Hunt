import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.Map.Entry.comparingByValue;

public class Decrypter {
    public static void main(String[] args) {
        String s = ("rhlc k jhrrknh sm qmrh wbsa sah wmqc koksay klc ymuq shkj lkjh sm nhs sah lhxs tguh");
        HashMap<Character,Integer> map = countOccurences(s);
        map = sortMap(map);
        printMap(map);
        HashMap<Character,Character> key = getKey(map);
        String ss = "";

        for(char c : s.toCharArray()){
            if(key.containsKey(c)){
                ss += key.get(c);
            }
            else{
                ss += " ";
            }
        }

        System.out.println(ss);

    }

    public static HashMap<Character,Integer> countOccurences(String s){
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1 );
            }
            else{
                map.put(c, 1);
            }
        }
        return map;
    }

    public static HashMap<Character, Integer> sortMap(HashMap map){
        List<Map.Entry<Character, Integer> > list =
                new LinkedList<Map.Entry<Character, Integer> >(map.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });


        // put data from sorted list to hashmap
        HashMap<Character, Integer> temp = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static HashMap<Character,Character> getKey(HashMap<Character,Integer> map){
        //remove spaces
        map.remove(' ');

        HashMap<Character, Character> key = new HashMap<>();
        char[] chars;
        String letters = "wyfbghmpducltnsoarie";
        chars = letters.toCharArray();
        int index = 0;
        for(char c : map.keySet()){
            System.out.println("Key: " + c + " Value: " + chars[index]);
            key.put(c,chars[index]);
            index++;
        }
        return key;
    }

    public static void printMap(HashMap map){
        for(Object o : map.keySet()){
            System.out.println("Key " + o + " Value " + map.get(o));
        }
    }
}

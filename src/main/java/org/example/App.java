package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       CraftedArrayList<String> craftArray = new CraftedArrayList<>(5);
       craftArray.add("Ok");
       craftArray.add("Hello");
       craftArray.add("Bye");
       for (int i = 0; i < craftArray.size(); i++) {
           System.out.println(craftArray.get(i));
       }

       CraftedLinkedList <Integer> craftLinked = new CraftedLinkedList<>();
       craftLinked.addFirst(2);
       craftLinked.addLast(1);
       for (int i = 0; i < craftLinked.size(); i++) {
           System.out.println(craftLinked.get(i));
       }
    }
}

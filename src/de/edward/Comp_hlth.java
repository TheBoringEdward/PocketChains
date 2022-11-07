package de.edward;

import java.util.*;

public class Comp_hlth implements Comparator <Character>{
    @Override public int compare(Character c1, Character c2){
        int re = 0;
        if(c1.getHlth() < c2.getHlth()){
            re = -10;
        } else if(c1.getHlth() > c2.getHlth()){
            re = 10;
        }
        return re;
    }
}

package lintcode.hash;

import java.util.Hashtable;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/22
 * @Description Two strings are isomorphic if the characters in s can be replaced to get t.
 *           All occurrences of a character must be replaced with another character while preserving the order of characters.
 *           No two characters may map to the same character but a character may map to itself.
 */
public class Isomorphic {
    public static void main(String[] args) {
        String s = "foo";
        String t = "bar";
        if (isIsomorphic(s, t)) {
            System.out.println("is isomorphic");
        } else {
            System.out.println("is not isomorphic");
        }
        // 两个字母映射为同一个 应该返回false
        s = "a`%ii,VEZQc_BSU%ObO5<sX81B/bOw+CNUd#Uav*P!Ax!#>hh,k#b/|>4ixFQZl+l!?bJjakbQbGglEb<g>Hf81m@A9GIvbd]qh?y__t+E(Iyv7zUEfZF{81VaM-0u?]tG=_fFR/XJ=X{-,oRpxE9u*VNYlM";
        t = "b`%ii-WE[Qc_BSV%OcO5<sX82B/cOw+CNVd#Vbv*P!Bx!#?hh-k#c/|?4ixFQ[l+l!?cJkbkcQcGhlEc<h?Hf82m@B9GIvcd]rh?y__t+E(Iyv7{VEf[F{82WbN/0u?]tG=_fFR/XJ=X{/-oRpxE9u*WNYlN";
        if (isIsomorphic(s, t)) {
            System.out.println("is isomorphic");
        } else {
            System.out.println("is not isomorphic");
        }
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        Hashtable<Character, Character> ht = new Hashtable<Character, Character>();
        Hashtable<Character, Character> htReverse = new Hashtable<Character, Character>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            Character key = s.charAt(i);
            Character val = t.charAt(i);
            if (ht.containsKey(key)) {
                Character valFirst = ht.get(key);
                if (val != valFirst) {
                    return false;
                }
            } else {
                ht.put(key, val);
            }
            if (htReverse.containsKey(val)) {
                Character keyFirst = htReverse.get(val);
                if (key != keyFirst) {
                    return false;
                }
            } else {
                htReverse.put(val, key);
            }
        }
        return true;
    }
}

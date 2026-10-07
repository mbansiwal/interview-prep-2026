package com.interview.blind75.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Encode and Decode Strings
 * LINK    : https://leetcode.com/problems/encode-and-decode-strings/
 *           (premium — free version: https://neetcode.io/problems/string-encode-and-decode)
 * DIFFICULTY: Medium
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Design an algorithm to encode a list of strings into a single string,
 * and decode that string back into the original list. Strings may
 * contain ANY character, including the delimiter you choose.
 *
 * EXAMPLES:
 *   Input : ["lint","code","love","you"]
 *   Encoded: "4#lint4#code4#love3#you"
 *   Output: ["lint","code","love","you"]
 *
 *   Input : ["we","say",":","yes"]
 *   Output: ["we","say",":","yes"]
 *
 * CONSTRAINTS:
 *   - 0 <= strs.length <= 200
 *   - 0 <= strs[i].length <= 200
 *   - strs[i] can contain any of the 256 ASCII characters
 *
 * ============================================================
 * APPROACH: Length Prefix — "len#str"
 * ============================================================
 * 1. encode: for each string write its length, then '#', then the string.
 * 2. decode: at position i, read digits up to the next '#' → that's len.
 * 3. The next len characters after '#' are the string — take them blindly.
 * 4. Jump i to the end of that string and repeat.
 *
 * WHY THIS WORKS:
 * A plain delimiter fails when the strings contain it. With a length
 * prefix we never search for a delimiter inside the data: we always know
 * exactly how many characters to take, so '#', digits, or empty strings
 * inside the data are harmless. The only '#' we look for is the one
 * right after the digits, and digits can't contain '#'.
 *
 * TIME  : O(N) encode and decode, N = total characters
 * SPACE : O(N) for the output (no extra working space)
 * ============================================================
 */
public class EncodeDecodeStrings {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    public List<String> decode(String encoded) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < encoded.length()) {
            int hash = encoded.indexOf('#', i);
            int len = Integer.parseInt(encoded.substring(i, hash));
            int start = hash + 1;
            result.add(encoded.substring(start, start + len));
            i = start + len;
        }
        return result;
    }
}

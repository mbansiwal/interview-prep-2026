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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Length prefix — "len#str"  (primary)
 *   1. encode: write each string's length, '#', then the string itself.
 *   2. decode: read digits up to the next '#' → len; take the next len chars blindly.
 *   Intuition: we never search for a delimiter inside the data, so any character is safe.
 *   TIME O(N) encode and decode, N = total characters · SPACE O(N) output only
 *
 * APPROACH 2: Escaping + terminator
 *   1. encode: double every '#' ("#" → "##"), then end each string with "#;".
 *   2. decode: scan; "##" → literal '#', "#;" → end of the current string.
 *   Intuition: after escaping, a single '#' followed by ';' can only be our terminator.
 *   TIME O(N) · SPACE O(N) output only
 *
 * WHICH TO USE:
 *   #1 is the standard answer (also how many wire protocols frame messages).
 *   #2 shows you understand escaping; it keeps the output readable but grows
 *   when the data contains many '#'. A plain delimiter without escaping is wrong.
 * ============================================================
 */
public class EncodeDecodeStrings {

    /** Approach 1 — length prefix encode. TIME O(N) · SPACE O(N) output */
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    /** Approach 1 — length prefix decode. TIME O(N) · SPACE O(N) output */
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

    /** Approach 2 — escape '#' as "##", terminate each string with "#;". TIME O(N) · SPACE O(N) output */
    public String encodeEscaped(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.replace("#", "##")).append("#;");
        }
        return sb.toString();
    }

    /** Approach 2 — decode "##" → '#', "#;" → end of string. TIME O(N) · SPACE O(N) output */
    public List<String> decodeEscaped(String encoded) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int i = 0;
        while (i < encoded.length()) {
            char c = encoded.charAt(i);
            if (c == '#' && encoded.charAt(i + 1) == '#') {
                current.append('#');
                i += 2;
            } else if (c == '#') { // must be "#;"
                result.add(current.toString());
                current.setLength(0);
                i += 2;
            } else {
                current.append(c);
                i++;
            }
        }
        return result;
    }
}

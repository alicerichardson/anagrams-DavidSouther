/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.google.engedu.anagrams;

import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    private List<String> wordList = new ArrayList<>();
    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            //Your code here
            wordList.add(word);
            String temp = sortLetters(word);
           if(!lettersToWord.containsKey(temp)) {
               lettersToWord.put(temp, new ArrayList<String>());
           }
            lettersToWord.get(temp).add(word);
        }
    }

    public boolean isGoodWord(String word, String base) {
        //will return true when word and base are anagrams and word does not equal base
        // Your code here
        if (base.equals(word)){
            return false;
        }
        else {
            if (isAnagram(base.toUpperCase(), word.toUpperCase())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public List<String> getAnagrams(String targetWord) {
        String sorted  = sortLetters(targetWord);
        if(!lettersToWord.containsKey(sorted)){
            return new ArrayList<>();
        }
        return lettersToWord.get(sorted);
    }

    @VisibleForTesting
    static boolean isAnagram(String first, String second) {
        //returns true if the second word is a valid anagram of the first word
        return sortLetters(first).equals(sortLetters(second));
    }

    @VisibleForTesting
    static String sortLetters(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        for (char c: alphabet){
            String temp = sortLetters(word + c);
            result.addAll(getAnagrams(temp));
        }
        return result;
    }

    public String pickGoodStarterWord() {
        //
        // Your code here
        //
        return "stop";
    }
}

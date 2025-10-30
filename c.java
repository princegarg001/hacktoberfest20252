class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagrams = new ArrayList<>();

        int stringLength = s.length();
        int patternLength = p.length();

        if(stringLength == 0 || patternLength == 0 || stringLength < patternLength)
            return anagrams;

        HashMap<Character, Integer> patternMap = new HashMap<>();
        HashMap<Character, Integer> stringMap = new HashMap<>();

        for(char ch : p.toCharArray())
            patternMap.put(ch, patternMap.getOrDefault(ch, 0) + 1);

        for(int index = 0; index < patternLength; index++){
            char ch = s.charAt(index);
            stringMap.put(ch, stringMap.getOrDefault(ch, 0) + 1);
        }

        if(stringMap.equals(patternMap))
            anagrams.add(0); 

        for(int index = patternLength; index < stringLength; index++){
            char endChar = s.charAt(index);
            stringMap.put(endChar, stringMap.getOrDefault(endChar, 0) + 1);

            char startChar = s.charAt(index - patternLength);
            if(stringMap.get(startChar) == 1)
                stringMap.remove(startChar);
            else
                stringMap.put(startChar, stringMap.get(startChar) - 1);

            if(stringMap.equals(patternMap))
                anagrams.add(index - patternLength + 1);
        }

        return anagrams;
    }
}

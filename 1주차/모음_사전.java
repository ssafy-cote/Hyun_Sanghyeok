class Solution {
    static String target = "";
    static boolean found = false;
    static int count = 0;
    static String[] words = {"A", "E", "I", "O", "U"};
    
    public int solution(String word) {
        target = word;
        dfs("");
        return count;
    }
    
    void dfs(String currentWord) {
        if (currentWord.length() > 5) {
            return;
        }
        
        if (currentWord.equals(target)) {
            found = true;
            return;
        }
        
        count++;
        for(int i = 0 ; i < 5 && !found ; i++) {
            dfs(currentWord + words[i]);
        }
    }
}

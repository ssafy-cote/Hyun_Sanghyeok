import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        // "1", "1xx", "1xxx", "2", "24", "59", "599", ...
        Arrays.sort(phone_book);       
        
        for (int i = 1 ; i < phone_book.length ; i++) {
            if (phone_book[i].startsWith(phone_book[i - 1])) {
                return false;
            }
        }

        return true;
    }
}

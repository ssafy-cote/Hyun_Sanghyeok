// 70,000kb / 0.15ms

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int count = 0;
        test: for(int i = 0 ; i < skill_trees.length ; i++) {
            int pointer = 0;
            String current = skill_trees[i];
            for(int j = 0 ; j < current.length() ; j++) {
                if(skill.substring(pointer).contains(String.valueOf(current.charAt(j)))) {
                    if (skill.charAt(pointer) == current.charAt(j)) {
                        pointer++;
                    } else {
                        continue test;
                    }
                }
            }
            count++;
        }
        return count;
    }
}

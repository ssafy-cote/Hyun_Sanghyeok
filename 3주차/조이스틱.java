class 조이스틱 {
	public int solution(String name) {
		int answer = 0;

		// 각 글자를 만들기 위해 몇 번의 위/아래 조작을 해야 하는가
		for (char c : name.toCharArray()) {
			answer += Math.min(c - 'A', ('Z' + 1) - c);
		}

		int length = name.length();
		int min = length - 1;

		// 어떤 지점에서 출발하고, 어떤 방향으로 돌아야 최소 이동을 할 수 있는지 계산
		for (int i = 0; i < length; i++) {
			int next = i + 1;
			while (next < length && name.charAt(next) == 'A') {
				next++;
			}
			min = Math.min(min, length - (next - i) + Math.min(i, length - next));
		}

		return answer + min;
	}
}

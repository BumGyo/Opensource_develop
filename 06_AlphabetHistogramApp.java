package homework7;

import java.util.Scanner;

public class AlphabetHistogramApp {
	private int[] alphabetCount; 
	
	public AlphabetHistogramApp() {
		alphabetCount = new int[26];
	}
	
	public void run() { // 키보드로부터 알파벳 문자를 읽고 히스토그램 그리기를 실행
		System.out.println("영문 텍스트를 입력하고 세미콜론을 입력하세요.");
		String inputText = readString(); // 입력받기
        makeHistogram(inputText); // 히스토그램 데이터 생성
        drawHistogram(); // 히스토그램 출력
	}
	
	private void makeHistogram(String text) { // 입력받은 문자들로부터 히스토그램의 데이터를 생성
		text = text.toLowerCase(); // 대소문자 구분 없앰

		for (char c : text.toCharArray()) {
			if (c >= 'a' && c <= 'z') { // 알파벳만 처리
				alphabetCount[c - 'a']++;
			}
		}
	}
	
	private void drawHistogram() { // 히스토그램을 그림
		System.out.println("히스토그램을 그립니다.");
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('A' + i);
            System.out.println(letter + "-".repeat(alphabetCount[i]));
        }
	}
	
	private String readString() { // 키보드로부터 문자열을 읽어 스트링버퍼에 저장
		StringBuffer sb = new StringBuffer();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String line = scanner.nextLine();
			if(line.equals(";"))
				break;
			sb.append(line);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		AlphabetHistogramApp app = new AlphabetHistogramApp();
        app.run(); // 프로그램 실행
	}
}

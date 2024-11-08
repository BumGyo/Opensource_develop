package homework8;

import java.util.*;
import java.io.*;

public class HangManGameApp {
    private String newWord;  // 선택된 단어
    private char[] hiddenWord;  // 숨겨진 단어 배열
    private Words words;  // 단어 리스트를 관리하는 Words 객체
    private int attempts;  // 남은 시도 횟수

    public HangManGameApp() {
        words = new Words("c:\\words.txt");  // words.txt 파일에서 단어를 읽음
        attempts = 5;  // 시도 횟수 5번으로 설정
    }

    // 게임을 시작하는 메소드
    public void run() {
    	System.out.println("지금부터 행맨 게임을 시작합니다.");
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            newWord = words.getRandomWord();  // 랜덤한 단어 선택
            makeHidden();  // 단어에서 두 글자를 숨김

            go();  // 행맨 게임 진행

            System.out.print("Next(y/n)?");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("y")) {
                playAgain = false;
            }
        }

        scanner.close();
    }

    // 선택된 단어 newWord에 2개의 글자를 숨긴 단어 hiddenWord를 만든다.
    private void makeHidden() {
        hiddenWord = newWord.toCharArray();
        Random random = new Random();
        int firstIndex = random.nextInt(newWord.length());
        int secondIndex;

        do {
            secondIndex = random.nextInt(newWord.length());
        } while (secondIndex == firstIndex);

        hiddenWord[firstIndex] = '-';
        hiddenWord[secondIndex] = '-';
    }

    // 사용자가 키를 입력받으면서 행맨 게임을 진행한다. 5번 틀리면 종료하며, 한 단어 진행 후 y/n 물음에 대해 n을 입력하면 종료한다.
    private void go() {
        Scanner scanner = new Scanner(System.in);
        int incorrectGuesses = 0;

        while (incorrectGuesses < attempts) {
            System.out.println(String.valueOf(hiddenWord));
            System.out.print(">>");
            char key = scanner.nextLine().charAt(0);

            if (!complete(key)) {
                incorrectGuesses++;
            }

            if (String.valueOf(hiddenWord).equals(newWord)) {
                System.out.println(newWord);
                return;
            }
        }

        System.out.println("5번 실패했습니다.");
    }

    // 사용자가 입력한 문자 key가 숨긴 글자와 일치하는지 검사하고, 일치하면 true를 리턴하고, hiddenWord의 '-' 문자를 key 문자로 변경한다.
    private boolean complete(char key) {
        boolean isCorrect = false;

        for (int i = 0; i < newWord.length(); i++) {
            if (newWord.charAt(i) == key && hiddenWord[i] == '-') {
                hiddenWord[i] = key;
                isCorrect = true;
            }
        }

        return isCorrect;
    }

    public static void main(String[] args) {
        HangManGameApp gameApp = new HangManGameApp();
        gameApp.run();
    }
}

class Words {
    private List<String> words;

    // 생성자: 파일로부터 단어를 읽어와 리스트에 저장
    public Words(String fileName) {
        words = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim();
                if (!word.isEmpty()) {  // 빈 줄은 무시
                    words.add(word);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다: " + fileName);
        }
    }

    // 랜덤하게 단어 하나를 반환하는 메소드
    public String getRandomWord() {
        if (words.isEmpty()) {
            return null;  // 리스트가 비어있으면 null 반환
        }
        
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        
        return words.get(randomIndex);  // 해당 인덱스의 단어 반환
    }
}
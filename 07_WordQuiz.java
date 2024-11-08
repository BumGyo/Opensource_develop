package homework8;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class Word { // 영어 단어와 그에 해당하는 한글 뜻을 저장하는 클래스
    private String english;
    private String korean;

    public Word(String english, String korean) {
        this.english = english;
        this.korean = korean;
    }

    public String getEnglish() {
        return english;
    }

    public String getKorean() {
        return korean;
    }
}

public class WordQuiz { // 퀴즈의 이름과 단어목록(Vector)을 관리하며, 퀴즈를 실행
    private Vector<Word> words;

    public WordQuiz(String name) { // 생성자: 프로그램 이름을 받고, 단어 벡터를 초기화
        words = new Vector<>();
        // 미리 여러 개의 Word 객체 삽입
        words.add(new Word("love", "사랑"));
        words.add(new Word("animal", "동물"));
        words.add(new Word("emotion", "감정"));
        words.add(new Word("human", "인간"));
        words.add(new Word("stock", "주식"));
        words.add(new Word("trade", "거래"));
        words.add(new Word("society", "사회"));
        words.add(new Word("baby", "아기"));
        words.add(new Word("honey", "꿀"));
        words.add(new Word("doll", "인형"));
        words.add(new Word("bear", "곰"));
        words.add(new Word("picture", "사진"));
        words.add(new Word("painting", "그림"));
        words.add(new Word("fault", "오류"));
        words.add(new Word("example", "보기"));
        words.add(new Word("eye", "눈"));
        words.add(new Word("statue", "조각상"));
    }

    // 보기 배열 생성
    private int[] makeExample(int answerIndex) {
        Random rand = new Random();
        int[] exampleIndices = new int[4];
        
        // 정답 인덱스를 무작위로 배치
        exampleIndices[0] = answerIndex;
        
        // 나머지 보기 인덱스를 랜덤하게 채움
        for (int i = 1; i < 4; i++) {
            int randIndex;
            do {
                randIndex = rand.nextInt(words.size());
            } while (exists(exampleIndices, randIndex));
            exampleIndices[i] = randIndex;
        }

        // 배열을 섞어서 정답이 항상 첫 번째가 아니도록 함
        for (int i = 0; i < 4; i++) {
            int randomPos = rand.nextInt(4);
            int temp = exampleIndices[i];
            exampleIndices[i] = exampleIndices[randomPos];
            exampleIndices[randomPos] = temp;
        }
        
        return exampleIndices;
    }

    // 배열에 특정 인덱스가 존재하는지 확인하는 함수
    private boolean exists(int[] array, int index) {
        for (int i : array) {
            if (i == index) return true;
        }
        return false;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\"명품영어\"의 단어 테스트를 시작합니다. -1을 입력하면 종료합니다.");
        System.out.print("현재 " + words.size() + "개의 단어가 들어 있습니다.");
        Random rand = new Random();
        
        while (true) {
            // 문제로 출제할 단어를 랜덤하게 선택
            int answerIndex = rand.nextInt(words.size());
            Word answerWord = words.get(answerIndex);
            
            // 보기 생성
            int[] exampleIndices = makeExample(answerIndex);
            
            System.out.println("\n" + answerWord.getEnglish() + "?");
            
            // 보기 출력
            for (int i = 0; i < 4; i++) {
                System.out.print("(" + (i + 1) + ")" + words.get(exampleIndices[i]).getKorean() + " ");
            }
            
            System.out.print(":>");
            
            int userAnswer;
            
            try {
                userAnswer = scanner.nextInt();
                
                if (userAnswer == -1) { 
                    System.out.println("\"명품영어\"를 종료합니다...");
                    break;
                }
                
                if (userAnswer < 1 || userAnswer > 4) { 
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
                }
                
                // 정답 확인
                if (exampleIndices[userAnswer - 1] == answerIndex) {
                    System.out.print("Excellent !!");
                } else {
                    System.out.print("No. !!");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력하세요.");
                scanner.next(); // 버퍼 비우기
            }
            
        }
        
        scanner.close();
    }

    public static void main(String[] args) { // 프로그램의 시작점. WordQuiz 객체 생성 후 실행
        WordQuiz quiz = new WordQuiz("명품영어");
        quiz.run();
    }
}
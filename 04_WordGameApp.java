package homework4;

import java.util.Scanner;

public class WordGameApp {
	private Player[] players;		// 배열로 플레이어 정보 저장
	private String word = "아버지";	// 시작 단어는 "아버지"
	
	public WordGameApp() { // 생성자 메소드
		createPlayers();
	}
	
	private void createPlayers() { // 게임 참가자수를 입력받고 Player[]을 생성하는 메소드
		Scanner scanner = new Scanner(System.in);
		System.out.println("끝말잇기 게임을 시작합니다...");
		System.out.print("게임에 참가하는 인원은 몇명입니까>>");
		int player_num = scanner.nextInt();
		
		players = new Player[player_num];
		for(int i = 0; i < player_num; i++) {
			System.out.print("참가자의 이름을 입력하세요>>");
			String player_name = scanner.next();
			players[i] = new Player(player_name);
		}
	}
	
	public Boolean checkSuccess(String lastWord, String newWord) { // lastWord와 newWord를 비교하는 메소드
		int lastIndex = lastWord.length() - 1;
		char lastChar = lastWord.charAt(lastIndex);
		char firstChar = newWord.charAt(0);
		return lastChar == firstChar;
	}
	
	public void run() { //게임을 진행하는 메소드
		System.out.println("시작하는 단어는 아버지입니다");
		String lastWord = word;

        int i = 0;
        while (true) {
            Player currentPlayer = players[i];
            String newWord = currentPlayer.getWordFromUser();

            if (!checkSuccess(lastWord, newWord)) {
                System.out.println(currentPlayer.getName() + "이 졌습니다.");
                break;
            }

            lastWord = newWord;
            i = (i + 1) % players.length;
        }
	}
	
	public static void main(String[] args) { // 메인 메소드
		WordGameApp game = new WordGameApp();
		game.run();
	}
}

class Player{
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getWordFromUser() { // 사용자로부터 단어를 입력받는 메소드
		Scanner scanner = new Scanner(System.in);
		System.out.print(this.name + ">>");
		return scanner.nextLine();
	}
	
	public String getName() {
        return name;
    }
	
}
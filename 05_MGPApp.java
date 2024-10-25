package homework6;

import java.util.Random;
import java.util.Scanner;

//선수를 추상화한 추상클래스
//next() 메소드가 추상 메소드이며, 상속받는 클래스는 next()를 구현
abstract class Player{
	protected String bet[] = {"묵", "찌", "빠"};
	protected String name; // 선수이름
	protected String lastBet= null; // 선수가 최근에 낸값
	
	protected Player(String name) { this.name = name; }
	public String getName() { return name; }
	public String getBet() { return lastBet; }
	abstract public String next(); // 선수가 낸것으로 묵찌빠중 1개를 결정하여 리턴
}

//사람 선수를 표현하는 클래스. Player클래스를 상속받아 next() 구현
class Human extends Player {
	private Scanner scanner= new Scanner(System.in);
	public Human(String name) {
		super(name);
	}

	@Override
	public String next() {
		System.out.print(name + ">>");
		lastBet = scanner.nextLine();
		return lastBet;
	}
}

//컴퓨터 선수를 표현하는 클래스. Player클래스를 상속받아 next() 구현
class Computer extends Player {
	private Random random = new Random();
	public Computer(String name) {
		super(name);
	}
	// 추상 메소드 구현
	@Override
	public String next() { // bet에서 랜덤하게 한 개 선택하여 리턴
		int choice = random.nextInt(bet.length);
		lastBet = bet[choice];
		//System.out.println(name + ">> 결정하였습니다.");
		return lastBet;
	}
	
}
class Game {
	private Player [] players = new Player[2]; // 두 명의 선수 객체
	private Scanner scanner= new Scanner(System.in);
	public Game() { }
	private void createPlayer() { // 2명의 선수 객체 생성
		System.out.println("***** 묵찌빠 게임을 시작합니다. *****");
		System.out.print("선수이름 입력>>");
		String playerName = scanner.next();
		players[0] = new Human(playerName);
		System.out.print("컴퓨터이름 입력>>");
		String computerName = scanner.next();
		players[1] = new Computer(computerName);
		System.out.println("2명이 선수를 생성 완료하였습니다...");
		System.out.println();
	}
	public void run() {
	    createPlayer();

	    // 가위바위보로 첫 공격자 결정
	    Player attacker;
	    Player defender;
	    
	    while (true) {
	        String humanChoice = players[0].next();
	        String computerChoice = players[1].next();
	        
	        System.out.println(players[0].getName() + ": " + humanChoice + ", " + players[1].getName() + ": " + computerChoice);
	        System.out.println();
	        
	        if (!humanChoice.equals(computerChoice)) {
	            if ((humanChoice.equals("가위") && computerChoice.equals("보")) ||
	                (humanChoice.equals("바위") && computerChoice.equals("가위")) ||
	                (humanChoice.equals("보") && computerChoice.equals("바위"))) {
	                attacker = players[0];
	                defender = players[1];
	            } else {
	                attacker = players[1];
	                defender = players[0];
	            }
	            break;
	        } else {
	            System.out.println("비겼습니다. 다시 시도합니다.");
	        }
	    }

	    // 묵찌빠 게임 시작
	    while (true) {
	        String attackerBet = attacker.next();
	        String defenderBet = defender.next();
	        
	        System.out.println(attacker.getName() + ": " + attackerBet + ", " + defender.getName() + ": " + defenderBet);
	        System.out.println();
	        
	        if (attackerBet.equals(defenderBet)) {
	            System.out.println(attacker.getName() + "이 이겼습니다");
	            System.out.println("게임을 종료합니다...");
	            break;
	        } else if((attackerBet.equals("묵") && defenderBet.equals("찌")) ||
	                   (attackerBet.equals("찌") && defenderBet.equals("빠")) ||
	                   (attackerBet.equals("빠") && defenderBet.equals("묵"))) {
	        	continue;
	        } else if((defenderBet.equals("묵") && attackerBet.equals("찌")) ||
	                   (defenderBet.equals("찌") && attackerBet.equals("빠")) ||
	                   (defenderBet.equals("빠") && attackerBet.equals("묵"))) {
	        	Player temp = attacker;
	            attacker = defender;
	            defender = temp;
	        }
	    }
	}
}

public class MGPApp{
	public static void main(String[] args) {
		new Game().run();
	}
}
import java.util.ArrayList;
import java.util.Scanner;

// consoleâ 
public class PersonMain {
	
	PersonDao dao = new PersonDao(); // ������=>1�ܰ� ����̹��ε�
	Scanner sc = new Scanner(System.in);
	
	PersonMain(){
		System.out.println("PersonMain()");
		init();
	}
	
	void init() {
		
		while(true) {
			System.out.println("\n=====�޴� �����ϱ�=====");
			System.out.println("1.��ü ���� ��ȸ");
			System.out.println("2.������ ��ȸ");
			System.out.println("3.���� ����");
			System.out.println("4.���� ����");
			System.out.println("5.���� �߰�");
			System.out.println("6.���α׷� ����");
			System.out.print(">> �޴� ��ȣ �Է�: ");
			int menu = sc.nextInt();
			
			switch(menu) {
			case 1: ArrayList<PersonBean> lists = dao.getAllPerson();
					showPerson(lists);
					break;
			
			case 2: inputGender();
				break;
			
			case 3: updateData();
				break;
				
			case 4: deleteData();
				break;
			
			case 5: insertData();
				break;
			
			case 6:
					System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
					break;
			default :
				System.out.println("1~6������ ��ȣ�� �Է°���");
				
			}
		}
	} // init()
	
	void deleteData() {
		
		System.out.print("������ ��ȣ �Է� : ");
		int num = sc.nextInt();
		
		int cnt = dao.deleteData(num);
		
		if(cnt == -1)
			System.out.println("���� ����");
		else if(cnt<= 0)
			System.out.println("���ǿ� �´� ������ ����");
		else
			System.out.println("���� ����");
	}
	
	
	void updateData() {
		
		// ��ȣ�� �������� ����
		System.out.print("������ ��ȣ �Է� : ");
		int num = sc.nextInt();
		
		System.out.print("������ �̸� �Է� : ");
		String name = sc.next();
		
		System.out.print("������ ���� �Է� : ");
		int age = sc.nextInt();
		
		System.out.print("������ ���� �Է� : ");
		String gender = sc.next();
		
		System.out.print("������ ������� �Է� : ");
		String birth = sc.next();
		
		PersonBean bean = new PersonBean();
		bean.setNum(num);
		bean.setName(name);
		bean.setAge(age);
		bean.setGender(gender);
		bean.setBirth(birth);
		
		//dao.updateData(num,name,age,gender,birth);
		int cnt = dao.updateData(bean);
		
		if(cnt == -1)
			System.out.println("���� ����");
		else if(cnt == 0)
			System.out.println("���ǿ� �´� ������ ����");
		else
			System.out.println("���� ����");
		
	}
	
	void insertData() {
		System.out.println("��ȣ�� �������� �Էµ˴ϴ�.(����)");
		System.out.print("�̸� �Է� : ");
		String name = sc.next();
		
		System.out.print("���� �Է� : ");
		int age = sc.nextInt();
		
		System.out.print("���� �Է� : ");
		String gender = sc.next();
		
		System.out.print("������� �Է� : ");
		String birth = sc.next();
		
		int cnt = dao.insertData(name,age,gender,birth);
		if(cnt < 0)
			System.out.println("insert ����");
		else
			System.out.println("insert ����");
		
	}
	
	void inputGender(){
		System.out.print("ã������ ���� �Է� : ");
		String findGender = sc.next(); // 
		
		ArrayList<PersonBean> lists = dao.getPersonByGender(findGender);
		if(lists.size() == 0)
			System.out.println("�ش� ������ �������� ����");
		
		else
			showPerson(lists);
	}
////////////////////////////////
//	  1 ����       23 ��      92/03/04
//    2 ����      31 ��     20/07/07
//    3 ����      44 ��     71/10/09
//    4 ����      11 ��     20/07/07
////////////////////////////////
	
	void showPerson(ArrayList<PersonBean> lists){
		
		String title = "��ȣ\t�̸�\t����\t����\t�������";
		System.out.println(title);
		
		for(int i=0;i<lists.size();i++) {
			String result = lists.get(i).getNum() + "\t" +
							lists.get(i).getName()  + "\t" +
							lists.get(i).getAge() + "\t" +
							lists.get(i).getGender() + "\t" +
							lists.get(i).getBirth();
			
			System.out.println(result);				
		
		
		}
		
	}
	
	public static void main(String[] args) {

		new PersonMain(); 
	}

}





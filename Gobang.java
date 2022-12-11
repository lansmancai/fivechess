import java.io.*;
import java.util.regex.*;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Date:3/12/2022<br>
 * @author jinniu
 * @version 1.0
 */
public class Gobang
{
	// �������̵Ĵ�С
	private static int BOARD_SIZE = 15;
	// ����һ����ά�������䵱����
	private String[][] board;
	public void initBoard()
	{
		// ��ʼ����������
		board = new String[BOARD_SIZE][BOARD_SIZE * 2];
		// ��ÿ��Ԫ�ظ�Ϊ"��"�������ڿ���̨��������
		for (var i = 0; i < BOARD_SIZE; i++)
		{
			for (var j = 0; j < BOARD_SIZE * 2; j++)
			{
				board[i][j] = "��";
			}
		}
	}
	// �ڿ���̨������̵ķ���
	public void printBoard()
	{
		// ��ӡÿ������Ԫ��
		for (var i = 0; i < BOARD_SIZE; i++)
		{
			for (var j = 0; j < BOARD_SIZE * 2; j++)
			{
				// ��ӡ����Ԫ�غ󲻻���
				System.out.print(board[i][j]);
			}
			// ÿ��ӡ��һ������Ԫ�غ����һ�����з�
			System.out.print("\n");
		}
	}
	// �����û������Ƿ�����������
	public void countuserwin(int x ,int y)
	{
		int[] index00 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index01 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] index10 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] index11 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index20 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index21 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index30 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index31 = {4, 3, 2, 1, 0, -1, -2, -3, -4};
		int sum0 = 0;
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		
		for (int i = 0; i < 5; i++)
		{   
			//����б�Խǹ��ĸ��������ѡ����X,Y�����9���㣬�����Ƿ��������5���Ӷ���һ�µģ�������������
			for (int j = i; j < i + 5; j++)
			{
				if (0 <= x + index00[j]&& x + index00[j] < BOARD_SIZE &&0 <= y + index01[j]&&y + index01[j] < BOARD_SIZE * 2&&board[x + index00[j]][y + index01[j]] == "��")
				{
					sum0++;
				}
				if (0 <= x + index10[j]&& x + index10[j] < BOARD_SIZE &&0 <= y + index11[j]&&y + index11[j] < BOARD_SIZE * 2&&board[x + index10[j]][y + index11[j]] == "��")
				{
					sum1++;
				}
			    if (0 <= x + index20[j]&& x + index20[j] < BOARD_SIZE &&0 <= y + index21[j]&&y + index21[j] < BOARD_SIZE * 2&&board[x + index20[j]][y + index21[j]] == "��")
				{
					sum2++;
				}
				if (0 <= x + index30[j]&& x + index30[j] < BOARD_SIZE &&0 <= y + index31[j]&&y + index31[j] < BOARD_SIZE * 2&&board[x + index30[j]][y + index31[j]] == "��")
				{
					sum3++;
				}
			}
			//sum0,sum1,sum2,sum3 4������һ��Ϊ5���������飬��ӡ��you win;
			if (sum0 == 5 || sum1 == 5 || sum2 == 5 || sum3 ==5)
			{
					System.out.println("you win!");
					printBoard();
					System.exit(0);
			}
			//û���������������ȫ�����������һ��
			sum0 = 0;
			sum1 = 0;
			sum2 = 0;
			sum3 = 0;
		}
	}
	//�����¼����������Ƿ�����������
	public void countcpwin(int x ,int y)
	{
		int[] index00 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index01 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] index10 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] index11 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index20 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index21 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index30 = {-4, -3, -2, -1, 0 , 1, 2, 3, 4};
		int[] index31 = {4, 3, 2, 1, 0, -1, -2, -3, -4};
		int sum0 = 0;
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		
		for (int i = 0; i < 5; i++)
		{   
			//����б�Խǹ��ĸ��������ѡ����X,Y�����9���㣬�����Ƿ��������5���Ӷ���һ�µģ�������������
			for (int j = i; j < i + 5; j++)
			{
				if (0 <= x + index00[j]&& x + index00[j] < BOARD_SIZE &&0 <= y + index01[j]&&y + index01[j] < BOARD_SIZE * 2&&board[x + index00[j]][y + index01[j]] == "��")
				{
					sum0++;
				}
				if (0 <= x + index10[j]&& x + index10[j] < BOARD_SIZE &&0 <= y + index11[j]&&y + index11[j] < BOARD_SIZE * 2&&board[x + index10[j]][y + index11[j]] == "��")
				{
					sum1++;
				}
			    if (0 <= x + index20[j]&& x + index20[j] < BOARD_SIZE &&0 <= y + index21[j]&&y + index21[j] < BOARD_SIZE * 2&&board[x + index20[j]][y + index21[j]] == "��")
				{
					sum2++;
				}
				if (0 <= x + index30[j]&& x + index30[j] < BOARD_SIZE &&0 <= y + index31[j]&&y + index31[j] < BOARD_SIZE * 2&&board[x + index30[j]][y + index31[j]] == "��")
				{
					sum3++;
				}
			}
			//sum0,sum1,sum2,sum3 4������һ��Ϊ5���������飬��ӡ��you win;
			if (sum0 == 5 || sum1 == 5 || sum2 == 5 || sum3 ==5)
			{
					System.out.println("cp win!");
					printBoard();
					System.exit(0);
			}
			//û���������������ȫ�����������һ��
			sum0 = 0;
			sum1 = 0;
			sum2 = 0;
			sum3 = 0;
		}
	}
			

  
	public static void main(String[] args) throws Exception
	{
		var gb = new Gobang();
		gb.initBoard();
		gb.printBoard();
		//�������Ի���������Դ�ļ�
		var myLocale = Locale.getDefault(Locale.Category.FORMAT);
		var bundle = ResourceBundle.getBundle("mess");
		//��ʾ�û�����
		System.out.println(bundle.getString("guide"));
		// �������ڻ�ȡ��������ķ���
		var br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine()��ÿ���ڼ���������һ�����ݰ��س����û�����������ݽ���br��ȡ����
		while ((inputStr = br.readLine()) != null)
		{
			//���������ж��û������Ƿ�Ϸ�
			Pattern p = Pattern.compile("\\d+?,\\d+?");
			Matcher m = p.matcher(inputStr);
			if (!m.matches())
			{
				System.out.println(bundle.getString("correct"));
				continue;
			}
			// ���û�������ַ����Զ��ţ�,����Ϊ�ָ������ָ���2���ַ���
			String[] posStrArr = inputStr.split(",");
			// ��2���ַ���ת�����û����������
			var xPos = Integer.parseInt(posStrArr[0]);
			var yPos = Integer.parseInt(posStrArr[1]);
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"�������������Ѿ��¹������ٴ������û��ύ���겢��������Ĳ������൱���ⲽ����
			if(gb.board[xPos - 1][yPos - 1] == "��" || gb.board[xPos - 1][yPos - 1] == "��")
			{
				gb.printBoard();
				System.out.println(bundle.getString("guide"));
				continue;
			}
			//�û����岢�����û������Ƿ�����������
			gb.board[xPos - 1][yPos - 1] = "��";
			gb.countuserwin(xPos - 1, yPos - 1);
			
			//�������ʼ������壬Ҫ���ϲ��ܸ��ǵ����¹�������꣬�����Ѿ��¹����������������,��������������֪������������
			int cpx = -1;
			int cpy = -1;
			int cpover = 0;
			while ( cpover < 1)
			{
				cpx = (int)(Math.random() * BOARD_SIZE);
				cpy = (int)(Math.random() * BOARD_SIZE * 2);
				//System.out.println(cpx);
				//System.out.println(cpy);
				if(gb.board[cpx][cpy] != "��" && gb.board[cpx][cpy] != "��")
				{
					gb.board[cpx][cpy] = "��";
					cpover++;
				}
			}
			gb.countcpwin(cpx, cpy);
			
			gb.printBoard();
			System.out.println(bundle.getString("guide"));
		}
	}
}

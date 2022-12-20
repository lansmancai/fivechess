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
	// 定义棋盘的大小
	private static int BOARD_SIZE = 15;
	// 定义一个二维数组来充当棋盘
	private String[][] board;
	public void initBoard()
	{
		// 初始化棋盘数组
		board = new String[BOARD_SIZE][BOARD_SIZE * 2];
		// 把每个元素赋为"╋"，用于在控制台画出棋盘
		for (var i = 0; i < BOARD_SIZE; i++)
		{
			for (var j = 0; j < BOARD_SIZE * 2; j++)
			{
				board[i][j] = "╋";
			}
		}
	}
	// 在控制台输出棋盘的方法
	public void printBoard()
	{
		// 打印每个数组元素
		for (var i = 0; i < BOARD_SIZE; i++)
		{
			for (var j = 0; j < BOARD_SIZE * 2; j++)
			{
				// 打印数组元素后不换行
				System.out.print(board[i][j]);
			}
			// 每打印完一行数组元素后输出一个换行符
			System.out.print("\n");
		}
	}
	// 计算用户输入是否导致五子连珠
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
			//横竖斜对角共四个方向，最多选包含X,Y坐标的9个点，计算是否存在连续5个子都是一致的，是则五子连珠
			for (int j = i; j < i + 5; j++)
			{
				if (0 <= x + index00[j]&& x + index00[j] < BOARD_SIZE &&0 <= y + index01[j]&&y + index01[j] < BOARD_SIZE * 2&&board[x + index00[j]][y + index01[j]] == "●")
				{
					sum0++;
				}
				if (0 <= x + index10[j]&& x + index10[j] < BOARD_SIZE &&0 <= y + index11[j]&&y + index11[j] < BOARD_SIZE * 2&&board[x + index10[j]][y + index11[j]] == "●")
				{
					sum1++;
				}
			    if (0 <= x + index20[j]&& x + index20[j] < BOARD_SIZE &&0 <= y + index21[j]&&y + index21[j] < BOARD_SIZE * 2&&board[x + index20[j]][y + index21[j]] == "●")
				{
					sum2++;
				}
				if (0 <= x + index30[j]&& x + index30[j] < BOARD_SIZE &&0 <= y + index31[j]&&y + index31[j] < BOARD_SIZE * 2&&board[x + index30[j]][y + index31[j]] == "●")
				{
					sum3++;
				}
			}
			//sum0,sum1,sum2,sum3 4个数有一个为5则五子连珠，打印出you win;
			if (sum0 == 5 || sum1 == 5 || sum2 == 5 || sum3 ==5)
			{
					System.out.println("you win!");
					printBoard();
					System.exit(0);
			}
			//没有五子连珠则计数全部归零进入下一轮
			sum0 = 0;
			sum1 = 0;
			sum2 = 0;
			sum3 = 0;
		}
	}
	//计算下计算机下棋后是否导致五子连珠
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
			//横竖斜对角共四个方向，最多选包含X,Y坐标的9个点，计算是否存在连续5个子都是一致的，是则五子连珠
			for (int j = i; j < i + 5; j++)
			{
				if (0 <= x + index00[j]&& x + index00[j] < BOARD_SIZE &&0 <= y + index01[j]&&y + index01[j] < BOARD_SIZE * 2&&board[x + index00[j]][y + index01[j]] == "★")
				{
					sum0++;
				}
				if (0 <= x + index10[j]&& x + index10[j] < BOARD_SIZE &&0 <= y + index11[j]&&y + index11[j] < BOARD_SIZE * 2&&board[x + index10[j]][y + index11[j]] == "★")
				{
					sum1++;
				}
			    if (0 <= x + index20[j]&& x + index20[j] < BOARD_SIZE &&0 <= y + index21[j]&&y + index21[j] < BOARD_SIZE * 2&&board[x + index20[j]][y + index21[j]] == "★")
				{
					sum2++;
				}
				if (0 <= x + index30[j]&& x + index30[j] < BOARD_SIZE &&0 <= y + index31[j]&&y + index31[j] < BOARD_SIZE * 2&&board[x + index30[j]][y + index31[j]] == "★")
				{
					sum3++;
				}
			}
			//sum0,sum1,sum2,sum3 4个数有一个为5则五子连珠，打印出you win;
			if (sum0 == 5 || sum1 == 5 || sum2 == 5 || sum3 ==5)
			{
					System.out.println("cp win!");
					printBoard();
					System.exit(0);
			}
			//没有五子连珠则计数全部归零进入下一轮
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
		//根据语言环境加载资源文件
		var myLocale = Locale.getDefault(Locale.Category.FORMAT);
		var bundle = ResourceBundle.getBundle("mess");
		//提示用户输入
		System.out.println(bundle.getString("guide"));
		// 这是用于获取键盘输入的方法
		var br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine()：每当在键盘上输入一行内容按回车，用户刚输入的内容将被br读取到。
		while ((inputStr = br.readLine()) != null)
		{
			//加入正则判断用户输入是否合法
			Pattern p = Pattern.compile("\\d+?,\\d+?");
			Matcher m = p.matcher(inputStr);
			if (!m.matches())
			{
				System.out.println(bundle.getString("correct"));
				continue;
			}
			// 将用户输入的字符串以逗号（,）作为分隔符，分隔成2个字符串
			try
			{
				String[] posStrArr = inputStr.split(",");
				// 将2个字符串转换成用户下棋的座标
				var xPos = Integer.parseInt(posStrArr[0]);
				var yPos = Integer.parseInt(posStrArr[1]);
				// 把对应的数组元素赋为"●"，如果这个坐标已经下过棋则再次提醒用户提交坐标并跳过下面的操作，相当于这步不算
				if(gb.board[xPos - 1][yPos - 1] == "●" || gb.board[xPos - 1][yPos - 1] == "★")
				{
					gb.printBoard();
					System.out.println(bundle.getString("guide"));
					continue;
				}
				//用户下棋并计算用户输入是否导致五子连珠
				gb.board[xPos - 1][yPos - 1] = "●";
				gb.countuserwin(xPos - 1, yPos - 1);
			}
			catch (Exception e)
			{
				System.out.println("您输入的坐标不合法，请重新输入，下棋坐标应以x,y的格式");
				continue;
			}

			
			//计算机开始随机下棋，要保障不能覆盖到已下过棋的坐标，坐标已经下过则再随机生成坐标,并计算计算机下棋知否导致五子连珠
			int cpx = -1;
			int cpy = -1;
			int cpover = 0;
			while ( cpover < 1)
			{
				cpx = (int)(Math.random() * BOARD_SIZE);
				cpy = (int)(Math.random() * BOARD_SIZE * 2);
				//System.out.println(cpx);
				//System.out.println(cpy);
				if(gb.board[cpx][cpy] != "●" && gb.board[cpx][cpy] != "★")
				{
					gb.board[cpx][cpy] = "★";
					cpover++;
				}
			}
			gb.countcpwin(cpx, cpy);
			
			gb.printBoard();
			System.out.println(bundle.getString("guide"));
		}
	}
}

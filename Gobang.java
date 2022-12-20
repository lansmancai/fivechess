
import java.util.*;
/**
 * Date:9/12/2022<br>
 * @author jinniu
 * @version 1.0
 */

public class ShowHand
{
	// 定义该游戏最多支持多少个玩家，
	private final int PLAY_NUM = 5;
	// 定义扑克牌的所有花色和数值.
	private String[] types = {"方块", "草花", "红心", "黑桃"};
	private String[] values = {"2", "3", "4", "5","6", "7", "8", "9", "10","J", "Q", "K", "A"};
	//ocards用来存储全部52张牌，用来获取每张牌在动态数组中的下标
	private List<String> ocards = new LinkedList<>();
	// cards是一局游戏中剩下的扑克牌
	private List<String> cards = new LinkedList<>();
	//定义所有玩家的分数，每一轮明牌更新分数，
	private int[] playerscore = new int[PLAY_NUM];
	//定义所有玩家的赌本，一开始都初始化为1000，每次下注最低100，最高500或剩余大于100的全部余额;
	private int[] playerbalance = new int[PLAY_NUM];
	// 定义所有的玩家
	private String[] players = new String[PLAY_NUM];
	//保存所有玩家的下注金
	private int[] playerzhu = new int[2];
	// 每个玩家维护一个List保存手里的牌
	private List<String>[] playersCards = new List[PLAY_NUM];
	/**
	 * 初始化扑克牌，放入52张扑克牌，
	 * 并且使用shuffle方法将它们按随机顺序排列
	 */
	public void initCards()
	{
		for (var i = 0; i < types.length; i++)
		{
			for (var j = 0; j < values.length; j++)
			{
				cards.add(types[i] + values[j]);
				//也保存一份到ocards
				ocards.add(types[i] + values[j]);
			}
		}
		// 随机排列
		Collections.shuffle(cards);
	}
	/**
	 * 初始化玩家，为每个玩家分派用户名。
	 */
	public void initPlayer(String... names)
	{
		if (names.length > PLAY_NUM || names.length < 2)
		{
			// 校验玩家数量，此处使用异常机制更合理
			System.out.println("玩家数量不对");
			return;
		}
		else
		{
			// 初始化玩家用户名
			for (var i = 0; i < names.length; i++)
			{
				players[i] = names[i];
				playerbalance[i] = 1000;
				playerzhu[i] = 0;
			}
		}
	}
	/**
	 * 初始化玩家手上的扑克牌，开始游戏时每个玩家手上的扑克牌为空，
	 * 程序使用一个长度为0的LinkedList来表示。
	 */
	public void initPlayerCards()
	{
		for (var i = 0; i < players.length; i++)
		{
			if (players[i] != null && !players[i].equals(""))
			{
				playersCards[i] = new LinkedList<String>();
			}
		}
	}
	/**
	 * 输出全部扑克牌，该方法没有实际作用，仅用作测试
	 */
	public void showAllCards()
	{
		for (var card : cards )
		{
			System.out.println(card);
		}
	}
	/**
	 * 添加一个测试用户并测试5张牌，这是用来测试规则是否正确，
	 */
	public void test()
	{
		for (var i = 0; i < players.length; i++)
		{
			if (players[i] == null || players[i].equals(""))
			{
				players[i] = "测试数据";
				playersCards[i] = new LinkedList<String>();
				playersCards[i].add("红心Q");
				playersCards[i].add("黑桃K");
				playersCards[i].add("方块7");
				playersCards[i].add("草花J");
				playersCards[i].add("方块A");
				break;
			}
		}
	}
	/**
	* 这函数用来计算5张牌牌面，给每个牌面类型一个100分值的区间，同类型也做了分值的区分，做到符合规则的先比类型再比大小再比花色
	* @param i是玩家在palyers的坐标
	*/
	public int countScore(int i)
	{
		List<Integer> cardvalue = new ArrayList<>();
		List<Integer> cardvalue2 = new ArrayList<>();
		for ( int j = 0; j < playersCards[i].size(); j++)
		{
		cardvalue.add(ocards.indexOf(playersCards[i].get(j)));
		}
		//System.out.println(cardvalue);
		Collections.sort(cardvalue);
		int max = 0;
		int score = 0;
		//判断是否是同花顺，是的话则打分返回,
		if(-1 < cardvalue.get(0) && cardvalue.get(cardvalue.size() - 1) < 13)
		{
			int sum = 0;
			for (int k = 0; k < cardvalue.size() - 1; k++)
			{
				if (cardvalue.get(k) + 1 == cardvalue.get(k+1))
				{
					sum++;
				}
			}
			if (sum == cardvalue.size() - 1) 
			{
				max = cardvalue.get(cardvalue.size() - 1);
				score = 800 + (max % 13) * 4 + 0;
				return score;
			}
		}
		if(12 < cardvalue.get(0) && cardvalue.get(cardvalue.size() - 1) < 26)
		{
			int sum = 0;
			for (int k = 0; k < cardvalue.size() - 1; k++)
			{
				if (cardvalue.get(k) + 1 == cardvalue.get(k+1))
				{
					sum++;
				}
			}
			if (sum == cardvalue.size() - 1) 
			{
				max = cardvalue.get(cardvalue.size() - 1);
				score = 800 + (max % 13) * 4 + 1;
				return score;
			}
		}
		if(25 < cardvalue.get(0) && cardvalue.get(cardvalue.size() - 1) < 39)
		{
			int sum = 0;
			for (int k = 0; k < cardvalue.size() - 1; k++)
			{
				if (cardvalue.get(k) + 1 == cardvalue.get(k+1))
				{
					sum++;
				}
			}
			if (sum == cardvalue.size() - 1) 
			{
				max = cardvalue.get(cardvalue.size() - 1);
				score = 800 + (max % 13) * 4 + 2;
				return score;
			}
		}
		if(38 < cardvalue.get(0) && cardvalue.get(cardvalue.size() - 1) < 52)
		{
			int sum = 0;
			for (int k = 0; k < cardvalue.size() - 1; k++)
			{
				if (cardvalue.get(k) + 1 == cardvalue.get(k+1))
				{
					sum++;
				}
			}
			if (sum == cardvalue.size() - 1) 
			{
				max = cardvalue.get(cardvalue.size() - 1);
				score = 800 + (max % 13) * 4 + 3;
				return score;
			}
		}
		for (int f = 0; f < cardvalue.size(); f++)
		{
			cardvalue2.add(cardvalue.get(f)%13);
		}
		Collections.sort(cardvalue2);
		//判断是否是四条，是则打分返回
		//System.out.println(cardvalue.toString());
		//System.out.println(cardvalue2.toString());
		for (int k = 0; k <= 1; k++)
		{
			if(cardvalue2.get(k+1) == cardvalue2.get(k) &&cardvalue2.get(k+2) == cardvalue2.get(k+1)&&cardvalue2.get(k+3) == cardvalue2.get(k+2))
			{
				score = 700 + cardvalue2.get(k+3);
				return score;
			}
		}
		//判断是否是三对+两对既满堂红，是则打分返回
		if(cardvalue2.get(0) == cardvalue2.get(1)&&cardvalue2.get(1) == cardvalue2.get(2)&&cardvalue2.get(3) == cardvalue2.get(4))
		{
			score = 600 + cardvalue2.get(0) ;
			return score;
		}
		if(cardvalue2.get(2) == cardvalue2.get(3)&&cardvalue2.get(3) == cardvalue2.get(4)&&cardvalue2.get(0) == cardvalue2.get(1))
		{
			score = 600 + cardvalue2.get(4);
			return score;
		}
		//判断是否是同花，是则打分返回
		if(-1 < cardvalue.get(0) && cardvalue.get(4) < 13)
		{
			score = 500 + (cardvalue.get(4) %13) * 4 + 0;
			return score;
		}
		if(12 < cardvalue.get(0) && cardvalue.get(4) < 26)
		{
			score = 500 + (cardvalue.get(4) %13) * 4 + 1;
			return score;
		}
		if(25 < cardvalue.get(0) && cardvalue.get(4) < 39)
		{
			score = 500 + (cardvalue.get(4) %13) * 4 + 2;
			return score;
		}
		if(38 < cardvalue.get(0) && cardvalue.get(4) < 52)
		{
			score = 500 + (cardvalue.get(4) %13) * 4 + 3;
			return score;
		}
		//判断是否是顺子，是则打分返回
		if(cardvalue2.get(4) == cardvalue2.get(3) + 1&&cardvalue2.get(3) == cardvalue2.get(2) + 1&&cardvalue2.get(2) == cardvalue2.get(1) + 1&&cardvalue2.get(1) == cardvalue2.get(0) + 1)
		{
			for(int k = 3; k > -1; k--)
			{ 
				if(cardvalue.indexOf(cardvalue2.get(4) + 13 * k) > -1)
				{
					score = 400 + cardvalue2.get(4) * 4 + k;
					break;
				}
			}
			return score;
		}
		//判断是否是三条，是则打分返回
		if(cardvalue2.get(0) == cardvalue2.get(1)&&cardvalue2.get(1) == cardvalue2.get(2))
		{
			score = 300 + cardvalue2.get(2);
			return score;
		}
		if(cardvalue2.get(1) == cardvalue2.get(2)&& cardvalue2.get(2) ==cardvalue2.get(3))
		{
			score = 300 + cardvalue2.get(3);
			return score;
		}
		if(cardvalue2.get(2) == cardvalue2.get(3)&&cardvalue2.get(3) == cardvalue2.get(4))
		{
			score = 300 + cardvalue2.get(4);
			return score;
		}
		//判断是否两对，是则打分返回
		int counttwodui = 0;
		int largedui = -1;
		for (int m = 0; m < 4; m++)
		{
			if (cardvalue2.get(m) == cardvalue2.get(m + 1))
			{
				counttwodui++;
				if (counttwodui == 2)
				{
					largedui = cardvalue2.get(m + 1);
					break;
				}
			}
		}
		if(largedui > -1)
		{
			for(int k = 3; k > -1; k--)
			{ 
				if(cardvalue.indexOf(largedui + 13 * k) > -1)
				{
					score = 200 + largedui * 4 + k;
					break;
				}
			}
			return score;
		}
		
		//判断是否一对，是则打回返回
		int countonedui = 0;
		int dui = -1;
		for (int n = 0; n < 4; n++)
		{
			if (cardvalue2.get(n) == cardvalue2.get(n + 1))
			{
				countonedui++;
				if (counttwodui == 1)
				{
					dui = cardvalue2.get(n + 1);
					break;
				}
			}
		}
		if(dui > -1)
		{
			for(int k = 3; k > -1; k--)
			{ 
				if(cardvalue.indexOf(dui + 13 * k) > -1)
				{
					score = 100 + dui * 4 + k;
					break;
				}
			}
			return score;
		}
		//走到这里那就是散牌了，散牌直接按最高牌面的牌打分
		for(int k = 3; k > -1; k--)
		{ 
			if(cardvalue.indexOf(cardvalue2.get(4) + 13 * k) > -1)
			{
				score =  cardvalue2.get(4)* 4 + k;
				break;
			}
		}
		return score;
	} 
	/**
	 * 派扑克牌
	 * @param first 最先派给谁
	 */
	public void deliverCard(String first)
	{
		// 调用ArrayUtils工具类的search方法，
		// 查询出指定元素在数组中的索引
		var firstPos = ArrayUtils.search(players, first);
		// 依次给位于该指定玩家之后的每个玩家派扑克牌
		for (var i = firstPos; i < PLAY_NUM; i++)
		{
			if (players[i] != null)
			{
				playersCards[i].add(cards.get(0));
				cards.remove(0);
			}
		}
		// 依次给位于该指定玩家之前的每个玩家派扑克牌
		for (var i = 0; i < firstPos; i++)
		{
			if (players[i] != null)
			{
				playersCards[i].add(cards.get(0));
				cards.remove(0);
			}
		}
	}
	/**
	 * 输出玩家手上的扑克牌
	 * 这是前4张牌需要隐藏第一张牌的输出
	 */
	public void showPlayerCards()
	{
		for (var i = 0; i < PLAY_NUM; i++)
		{
			if (players[i] != null)
			{
				System.out.print(players[i] + ":\t" );
				for (var j = 0; j < playersCards[i].size(); j++)
				{
					if (j == 0)
					{
						System.out.print("*\t");
					}
					else
					{
						System.out.print(playersCards[i].get(j) + "\t");
					}
				}
				System.out.println();
			}
		}
		System.out.println();
		for (var i = 0; i < PLAY_NUM; i++)
		{
			if (players[i] != null && players[i].equals("西门吹雪"))
			{
				System.out.println(players[i] + "的底牌:\t" + playersCards[i].get(0));
			}
		}
		System.out.println();
	}
	/**
	 * 输出玩家手上的扑克牌
	 * 这是第5张牌时全部输出，包括底牌
	 */
	public void showPlayerallCards()
	{
		for (var i = 0; i < PLAY_NUM; i++)
		{
			if (players[i] != null)
			{
				System.out.print(players[i] + ":\t" );
				for (var j = 0; j < playersCards[i].size(); j++)
				{
						System.out.print(playersCards[i].get(j) + "\t");
				}
			}
			System.out.print("\n");
		}
	}
	/**
	*展示每个账户余额
	*/
	public void showPlayerbalance()
	{
		for (var i = 0; i < PLAY_NUM; i++)
		{
			if (players[i] != null)
			{
				System.out.print(players[i] + "账户余额:\t"+playerbalance[i]);
			}
			System.out.print("\n");
		}
	}
	/**
	*展示每个账户下注金额池
	*/
	public void showPlayerzhu()
	{
		for (var i = 0; i < PLAY_NUM; i++)
		{
			if (players[i] != null)
			{
				System.out.print(players[i] + "下注金额池:\t"+playerzhu[i]);
			}
			System.out.print("\n");
		}
	}
	/**
	 * 按照分数给出类型
	 * @param score是之前计算出来的得分
	 */
	public String counttype(int score)
	{
		if(score < 900 && score >= 800)
		{
			return "同花顺";
		}
		if(score < 800 && score >= 700)
		{
			return "四条";
		}
		if(score < 700 && score >= 600)
		{
			return "满堂红";
		}
		if(score < 600 && score >= 500)
		{
			return "同花";
		}
		if(score < 500 && score >= 400)
		{
			return "顺子";
		}
		if(score < 400 && score >= 300)
		{
			return "三条";
		}
		if(score < 300 && score >= 200)
		{
			return "两对";
		}
		if(score < 200 && score >= 100)
		{
			return "一对";
		}
		return "散牌";
	}
	/**
	 * 按照分数给出类型
	 * @param round是第几轮，前4轮只计算单张明牌的牌面分数并存储，第5轮则计算全部牌的分数
	 */
	public void count(int round)
	{
		System.out.println();
		if (round < 5)
		{
			showPlayerbalance();
			showPlayerzhu();
			showPlayerCards();
			for (var i = 0; i < players.length; i++)
			{
				if (players[i] != null && !players[i].equals(""))
				{
					int idex = ocards.indexOf(playersCards[i].get(round - 1));
					int num = idex%13;
					for(int k = 3; k > -1; k--)
					{ 
						if(idex == num + 13 * k)
						{
							int score = num * 4 + k;
							playerscore[i] = score;
							break;
						}
					}
					System.out.print(players[i]+"最新牌面值:\t");
					System.out.println(playerscore[i]);
				}
				
			}
		}
		if (round == 5)
		{
			showPlayerbalance();
			showPlayerzhu();
			showPlayerallCards();
			for (var i = 0; i < players.length; i++)
			{
				if (players[i] != null && !players[i].equals(""))
				{
					playerscore[i] = countScore(i);
					System.out.print(players[i]+"全部5张牌牌面值:\t(");
					System.out.print(counttype(playerscore[i]) + ")*************(");
					System.out.println(playerscore[i] + ")");
				}
			}
		}
		System.out.println();
	}
	/**
	* countwin函数用来计算第2，3，4轮发牌后是计算机和用户牌面比较，如果用户放弃则计算机直接胜出，用户投注额转给计算机，
	* 若计算机牌面大则下注100，用户可以选择follow或add或giveup.若用户牌面大则计算机一直选择跟随，金额不足跟随则计算机输
	*/
	public void countwin()
	{
		//计算出是计算机牌面大还是用户牌面大
		int win = playerscore[0] > playerscore[1]? 0:1;
		//计算机牌面大则下注100，然后让用户选择；
		if(win == 0)
		{   
			System.out.println("人工智能牌面大,人工智能下注100");
			playerbalance[0] -= 100;
			playerzhu[0] += 100;
			showPlayerbalance();
			showPlayerzhu();
			Scanner scstr = new Scanner(System.in);
			Scanner scint = new Scanner(System.in);
			System.out.println("用户输入follow跟随，输入add加注，输入give up!放弃");
			String str1 = scstr.nextLine();
			if (str1.equals("give up!"))
			{
				System.out.println("用户放弃，计算机赢得此局");
				playerbalance[0] += playerzhu[0] + playerzhu[1];
				playerzhu[0] = 0;
				playerzhu[1] = 0;
				showPlayerbalance();
				showPlayerzhu();
				System.exit(0);
			}
			if (str1.equals("follow"))
			{
				playerbalance[1] -= 100;
				playerzhu[1] += 100;
				System.out.println("用户选择了跟随");
				showPlayerbalance();
				showPlayerzhu();
			}
			if (str1.equals("add"))
			{
				System.out.println("用户选择了加注，请输入你加注的金额，金额至少100，不超过300");
				int addnum = scint.nextInt();
				if (addnum > playerbalance[1] || addnum < 100)
				{
					System.out.println("金额不足，用户犯规，游戏结束");
					showPlayerbalance();
					showPlayerzhu();
					System.exit(0);
				}
				playerbalance[1] -= addnum;
				playerzhu[1] += addnum;
				System.out.println("用户加注金额:" + addnum);
				showPlayerbalance();
				showPlayerzhu();
			}
		}
		//用户牌面大则自己下注，计算机都是跟随
		if ( win == 1)
		{
			System.out.println("用户牌面大,请用户输入下注金额，金额至少100，最多300:");
			Scanner userscint = new Scanner(System.in);
			int zhunum = userscint.nextInt();
			if (zhunum > playerbalance[1] || zhunum <100 || zhunum >300)
			{
				System.out.println("用户犯规，游戏结束");
				showPlayerbalance();
				showPlayerzhu();
				System.exit(0);
			}
				
			playerbalance[1] -= zhunum;
			playerzhu[1] += zhunum;
			if (zhunum > playerbalance[0])
			{
				System.out.println("人工智能余额不够跟随，用户胜出，游戏结束");
			}
			playerbalance[0] -= zhunum;
			playerzhu[0] += zhunum;
			showPlayerbalance();
			showPlayerzhu();
		}
	}
	/**
	* countwinlast函数用来计算第5轮发牌后,决战胜负
	*/
	public void countwinlast()
	{
		int lastwin = playerscore[0] > playerscore[1]? 0:1;
		if (lastwin  == 0)
		{
			playerbalance[0] += playerzhu[0] + playerzhu[1];
			playerzhu[0] = 0;
			playerzhu[1] = 0;
			System.out.println("此局人工智能获胜,游戏结束！");
			System.out.println("*************************");
			showPlayerbalance();
			showPlayerzhu();
			System.exit(0);
		}
		if (lastwin  == 1)
		{
			playerbalance[1] += playerzhu[0] + playerzhu[1];
			playerzhu[0] = 0;
			playerzhu[1] = 0;
			System.out.println("此局西门吹雪获胜,游戏结束！");
			System.out.println("*******************************************************");
			showPlayerbalance();
			showPlayerzhu();
			System.exit(0);
		}
	}
	/**
	* 计算谁的牌面大决定谁先发牌
	*/
	public void fapai()
	{
		int first = playerscore[0] > playerscore[1]? 0:1;
		System.out.println("从" + players[first] + "开始发牌");
		deliverCard(players[first]);
	}
	
	
	public static void main(String[] args)
	{
		var sh = new ShowHand();
		sh.initPlayer("人工智能", "西门吹雪");
		sh.initCards();
		sh.initPlayerCards();
		//sh.showAllCards();
		System.out.println("***************************游戏开始***************************");
		System.out.println();
		//第1轮发牌
		System.out.println("--------------------------第1轮发牌--------------------------");
		System.out.println();
		System.out.println("从西门吹雪开始发牌");
		System.out.println();
		sh.deliverCard("西门吹雪");
		sh.showPlayerbalance();
		sh.showPlayerzhu();
		sh.showPlayerCards();
		//第2轮发牌
		System.out.println("--------------------------第2轮发牌--------------------------");
		System.out.println();
		System.out.println("从人工智能开始发牌");
		System.out.println();
		sh.deliverCard("人工智能");
		sh.count(2);
		try
		{
			sh.countwin();
		}
		catch (Exception e)
		{
			System.out.println("用户输入不合法，游戏结束");
			System.exit(0);
		}
		//第3轮发牌
		System.out.println("--------------------------第3轮发牌--------------------------");
		System.out.println();
		sh.fapai();
		sh.count(3);
		try
		{
			sh.countwin();
		}
		catch (Exception e)
		{
			System.out.println("用户输入不合法，游戏结束");
			System.exit(0);
		}
		//第4轮发牌
		System.out.println("--------------------------第4轮发牌--------------------------");
		System.out.println();
		sh.fapai();
		sh.count(4);
		try
		{
			sh.countwin();
		}
		catch (Exception e)
		{
			System.out.println("用户输入不合法，游戏结束");
			System.exit(0);
		}
		//第5轮发牌
		System.out.println("--------------------------第5轮发牌--------------------------");
		System.out.println();
		sh.fapai();
		//sh.test();
		sh.count(5);
		sh.countwinlast();
		//sh.test();
	}
}

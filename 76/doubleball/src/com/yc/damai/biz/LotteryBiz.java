package com.yc.damai.biz;

import java.util.List;

import com.yc.damai.bean.Lottery;
import com.yc.damai.dao.LotteryDao;
/**
 * ����LotteryDao��������
 * @author psq
 *
 */
public class LotteryBiz {
	LotteryDao dao = new LotteryDao();
	//��ѯ���ж�Ӧ���¼�
	public List<Lottery> query(Lottery lottery) throws Exception{
		return dao.selectAll(lottery);
	}
	//�������
	public int add(Lottery lottery) throws Exception {
		return dao.insert(lottery);
	}
}

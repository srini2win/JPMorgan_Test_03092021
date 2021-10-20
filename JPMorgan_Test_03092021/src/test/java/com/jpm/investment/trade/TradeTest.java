package com.jpm.investment.trade;

import org.junit.Before;
import org.junit.Test;

import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.model.TradePosition;
import com.jpm.investment.trade.model.TradePositionIdentifier;
import com.jpm.investment.trade.util.TradeDirection;
import com.jpm.investment.trade.util.TradeOperation;

import junit.framework.TestCase;

public class TradeTest {
	TradeStore store;

	private Trade constructTrade(String string) {
		String[] split = string.split("\\|");
		Trade t = new Trade(new Integer(split[0]), new Integer(split[1]));
		t.setSecurityId(split[2]);
		t.setQuantity(new Integer(split[3]));
		t.setDirection(TradeDirection.valueOf(split[4]));
		t.setAccountNumber(split[5]);
		t.setOperation(TradeOperation.valueOf(split[6]));
		return t;
	}

	@Before
	public void setup() {
		store = new TradeStore();
	}

	@Test
	public void testAcc1234() throws Exception {
		Trade t1 = new Trade(1234, 1);
		t1.setAccountNumber("ACC-1234");
		t1.setSecurityId("XYZ");
		t1.setDirection(TradeDirection.BUY);
		t1.setOperation(TradeOperation.NEW);
		t1.setQuantity(100);

		Trade t2 = new Trade(1234, 2);
		t2.setAccountNumber("ACC-1234");
		t2.setSecurityId("XYZ");
		t2.setDirection(TradeDirection.BUY);
		t2.setOperation(TradeOperation.AMEND);
		t2.setQuantity(150);

		store.collectTrade(t1);
		store.collectTrade(t2);
		TradePosition position = store.getTradeCollection().getPositionsList().get(0);
		TestCase.assertEquals("[1234]", position.getTrades().toString());
		TestCase.assertEquals(new Integer("150"), position.getQuantity());
		TestCase.assertEquals("PositionIdentifier [accountNumber=ACC-1234, instrument=XYZ]",
				position.getId().toString());
	}

	@Test
	public void testAcc2345() throws Exception{
		TradeStore builder = new TradeStore();
		builder.collectTrade(constructTrade("5678|1|QED|200|BUY|ACC-2345|NEW"));
		builder.collectTrade(constructTrade("5678|2|QED|0|BUY|ACC-2345|CANCEL"));
		calculateTradePosition(builder, "ACC-2345|QED|0|5678");
	}

	@Test
	public void testAcc3456() throws Exception{
		TradeStore builder = new TradeStore();
		builder.collectTrade(constructTrade("2233|1|RET|100|SELL|ACC-3456|NEW"));
		builder.collectTrade(constructTrade("2233|2|RET|400|SELL|ACC-3456|AMEND"));
		builder.collectTrade(constructTrade("2233|3|RET|0|SELL|ACC-3456|CANCEL"));
		calculateTradePosition(builder, "ACC-3456|RET|0|2233");
	}

	@Test
	public void testAcc4567() throws Exception{
		TradeStore builder = new TradeStore();
		builder.collectTrade(constructTrade("8896|1|YUI|300|BUY|ACC-4567|NEW"));
		builder.collectTrade(constructTrade("6638|1|YUI|100|SELL|ACC-4567|NEW"));
		calculateTradePosition(builder, "ACC-4567|YUI|200|8896,6638");
	}

	@Test
	public void testAcc5678() throws Exception {
		TradeStore builder = new TradeStore();
		builder.collectTrade(constructTrade("6363|1|HJK|200|BUY|ACC-5678|NEW"));
		builder.collectTrade(constructTrade("7666|1|HJK|200|BUY|ACC-5678|NEW"));
		builder.collectTrade(constructTrade("6363|2|HJK|100|BUY|ACC-5678|AMEND"));
		builder.collectTrade(constructTrade("7666|2|HJK|50|SELL|ACC-5678|AMEND"));
		calculateTradePosition(builder, "ACC-5678|HJK|50|6363,7666");
	}

	@Test
	public void testAcc6789() throws Exception {
		TradeStore builder = new TradeStore();
		builder.collectTrade(constructTrade("8686|1|FVB|100|BUY|ACC-6789|NEW"));
		builder.collectTrade(constructTrade("8686|2|GBN|100|BUY|ACC-6789|AMEND"));
		builder.collectTrade(constructTrade("9654|1|FVB|200|BUY|ACC-6789|NEW"));
		calculateTradePosition(builder, "ACC-6789|GBN|100|8686");
		calculateTradePosition(builder, "ACC-6789|FVB|200|9654,8686");
	}

	@Test
	public void testAcc7789() throws Exception {
		TradeStore builder = new TradeStore();
		builder.collectTrade(constructTrade("1025|1|JKL|100|BUY|ACC-7789|NEW"));
		builder.collectTrade(constructTrade("1036|1|JKL|100|BUY|ACC-7789|NEW"));
		builder.collectTrade(constructTrade("1025|2|JKL|100|SELL|ACC-8877|AMEND"));
		calculateTradePosition(builder, "ACC-7789|JKL|100|1036,1025");
		calculateTradePosition(builder, "ACC-8877|JKL|-100|1025");
	}

	@Test
	public void testAcc9045() throws Exception {
		TradeStore builder = new TradeStore();
		builder.collectTrade(constructTrade("1122|1|KLO|100|BUY|ACC-9045|NEW"));
		builder.collectTrade(constructTrade("1122|2|HJK|100|SELL|ACC-9045|AMEND"));
		builder.collectTrade(constructTrade("1122|3|KLO|100|SELL|ACC-9045|AMEND"));
		builder.collectTrade(constructTrade("1144|1|KLO|300|BUY|ACC-9045|NEW"));
		builder.collectTrade(constructTrade("1144|2|KLO|400|BUY|ACC-9045|AMEND"));
		builder.collectTrade(constructTrade("1155|1|KLO|600|SELL|ACC-9045|NEW"));
		builder.collectTrade(constructTrade("1155|2|KLO|0|BUY|ACC-9045|CANCEL"));

		calculateTradePosition(builder, "ACC-9045|KLO|300|1122,1144,1155");
		calculateTradePosition(builder, "ACC-9045|HJK|0|1122");
	}

	@Test
	public void TradeBuildTest() throws Exception {
		store.collectTrade(constructTrade("1234|1|XYZ|100|BUY|ACC-1234|NEW"));
		store.collectTrade(constructTrade("1234|2|XYZ|150|BUY|ACC-1234|AMEND"));
		store.collectTrade(constructTrade("5678|1|QED|200|BUY|ACC-2345|NEW"));
		store.collectTrade(constructTrade("7897|2|QED|0|BUY|ACC-2345|CANCEL"));
		store.collectTrade(constructTrade("2233|1|RET|100|SELL|ACC-3456|NEW"));
		store.collectTrade(constructTrade("2233|2|RET|400|SELL|ACC-3456|AMEND"));
		store.collectTrade(constructTrade("2233|3|RET|0|SELL|ACC-3456|CANCEL"));
		store.collectTrade(constructTrade("8896|1|YUI|300|BUY|ACC-4567|NEW"));
		store.collectTrade(constructTrade("6638|1|YUI|100|SELL|ACC-4567|NEW"));
		store.collectTrade(constructTrade("6363|1|HJK|200|BUY|ACC-5678|NEW"));
		store.collectTrade(constructTrade("7666|1|HJK|200|BUY|ACC-5678|NEW"));
		store.collectTrade(constructTrade("6363|2|HJK|100|BUY|ACC-5678|AMEND"));
		store.collectTrade(constructTrade("7666|2|HJK|50|SELL|ACC-5678|AMEND"));
		store.collectTrade(constructTrade("8686|1|FVB|100|BUY|ACC-6789|NEW"));
		store.collectTrade(constructTrade("8686|2|GBN|100|BUY|ACC-6789|AMEND"));
		store.collectTrade(constructTrade("9654|1|FVB|200|BUY|ACC-6789|NEW"));
		store.collectTrade(constructTrade("1025|1|JKL|100|BUY|ACC-7789|NEW"));
		store.collectTrade(constructTrade("1036|1|JKL|100|BUY|ACC-7789|NEW"));
		store.collectTrade(constructTrade("1025|2|JKL|100|SELL|ACC-8877|AMEND"));
		store.collectTrade(constructTrade("1122|1|KLO|100|BUY|ACC-9045|NEW"));
		store.collectTrade(constructTrade("1122|2|HJK|100|SELL|ACC-9045|AMEND"));
		store.collectTrade(constructTrade("1122|3|KLO|100|SELL|ACC-9045|AMEND"));
		store.collectTrade(constructTrade("1144|1|KLO|300|BUY|ACC-9045|NEW"));
		store.collectTrade(constructTrade("1144|2|KLO|400|BUY|ACC-9045|AMEND"));
		store.collectTrade(constructTrade("1155|1|KLO|600|SELL|ACC-9045|NEW"));
		store.collectTrade(constructTrade("1155|2|KLO|0|BUY|ACC-9045|CANCEL"));


		calculateTradePosition(store, "ACC-1234|XYZ|150|1234");
		calculateTradePosition(store, "ACC-2345|QED|200|5678,7897");
		calculateTradePosition(store, "ACC-3456|RET|0|2233");
		calculateTradePosition(store, "ACC-4567|YUI|200|8896,6638");
		calculateTradePosition(store, "ACC-5678|HJK|50|6363,7666");
		calculateTradePosition(store, "ACC-6789|GBN|100|8686");
		calculateTradePosition(store, "ACC-6789|FVB|200|9654,8686");
		calculateTradePosition(store, "ACC-7789|JKL|100|1036,1025");
		calculateTradePosition(store, "ACC-8877|JKL|-100|1025");
	}

	private void calculateTradePosition(TradeStore builder, String string) {
		String[] split = string.split("\\|");
		TradePositionIdentifier id = new TradePositionIdentifier(split[0], split[1]);
		TradePosition position = builder.getTradeCollection().getPositionById(id);
		TestCase.assertEquals(position.getQuantity().toString(), split[2]);
		String[] trades = split[3].split(",");
		TestCase.assertEquals(trades.length, position.getTrades().size());
		for (String trade : trades) {
			TestCase.assertTrue(position.getTrades().contains(new Integer(trade)));
		}
	}

}

package com.jpm.investment.trade.util;

import com.jpm.investment.trade.TradeStore;
import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.model.TradePosition;
import com.jpm.investment.trade.model.TradePositionIdentifier;

import junit.framework.TestCase;

public class TradeUtil {

	public static Trade buildTrade(String string) {
		String[] split = string.split("\\|");
		Trade t = new Trade(new Integer(split[0]), new Integer(split[1]));
		t.setSecurityId(split[2]);
		t.setQuantity(new Integer(split[3]));
		t.setDirection(TradeDirection.valueOf(split[4]));
		t.setAccountNumber(split[5]);
		t.setOperation(TradeOperation.valueOf(split[6]));
		return t;
	}

	public static void calculateTradePosition(TradeStore builder, String string) {
		String[] split = string.split("\\|");
		TradePositionIdentifier id = new TradePositionIdentifier(split[0], split[1]);
		TradePosition position = builder.getTradeCollection().getPositionById(id);
		TestCase.assertEquals(position.getQuantity().toString(), split[2]);
		String[] trades = split[3].split(",");
		TestCase.assertEquals(trades.length, position.getTrades().size());
		System.out.println("Trade Acc " + position.getId().getAccountNumber());
		System.out.println("Trade Instrument " + position.getId().getInstrument());

		for (Integer trade : position.getTrades()) {
			System.out.println("Trades  " + trade);
		}
	}

}

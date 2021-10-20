package com.jpm.investment.trade.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.jpm.investment.trade.TradeStore;
import com.jpm.investment.trade.model.TradePosition;
import com.jpm.investment.trade.model.TradePositionIdentifier;

public class Trademain {
	final static Logger logger = Logger.getLogger(Trademain.class);

	public static void main(String[] args) {

		try {
			TradeStore store = new TradeStore();
			// To keep the solution simple i created manual input of streaming trades like
			// this and build the trade
			// we can use filereader to read a file of trades and use
			// concurrentAccountHashMap/Blocking Queue for processing
			store.collectTrade(TradeUtil.buildTrade("1234|1|XYZ|100|BUY|ACC-1234|NEW"));
			store.collectTrade(TradeUtil.buildTrade("1234|2|XYZ|150|BUY|ACC-1234|AMEND"));
			store.collectTrade(TradeUtil.buildTrade("5678|1|QED|200|BUY|ACC-2345|NEW"));
			store.collectTrade(TradeUtil.buildTrade("7897|2|QED|0|BUY|ACC-2345|CANCEL"));
			store.collectTrade(TradeUtil.buildTrade("2233|1|RET|100|SELL|ACC-3456|NEW"));
			store.collectTrade(TradeUtil.buildTrade("2233|2|RET|400|SELL|ACC-3456|AMEND"));
			store.collectTrade(TradeUtil.buildTrade("2233|3|RET|0|SELL|ACC-3456|CANCEL"));
			store.collectTrade(TradeUtil.buildTrade("8896|1|YUI|300|BUY|ACC-4567|NEW"));
			store.collectTrade(TradeUtil.buildTrade("6638|1|YUI|100|SELL|ACC-4567|NEW"));
			store.collectTrade(TradeUtil.buildTrade("6363|1|HJK|200|BUY|ACC-5678|NEW"));
			store.collectTrade(TradeUtil.buildTrade("7666|1|HJK|200|BUY|ACC-5678|NEW"));
			store.collectTrade(TradeUtil.buildTrade("6363|2|HJK|100|BUY|ACC-5678|AMEND"));
			store.collectTrade(TradeUtil.buildTrade("7666|2|HJK|50|SELL|ACC-5678|AMEND"));
			store.collectTrade(TradeUtil.buildTrade("8686|1|FVB|100|BUY|ACC-6789|NEW"));
			store.collectTrade(TradeUtil.buildTrade("8686|2|GBN|100|BUY|ACC-6789|AMEND"));
			store.collectTrade(TradeUtil.buildTrade("9654|1|FVB|200|BUY|ACC-6789|NEW"));
			store.collectTrade(TradeUtil.buildTrade("1025|1|JKL|100|BUY|ACC-7789|NEW"));
			store.collectTrade(TradeUtil.buildTrade("1036|1|JKL|100|BUY|ACC-7789|NEW"));
			store.collectTrade(TradeUtil.buildTrade("1025|2|JKL|100|SELL|ACC-8877|AMEND"));
			store.collectTrade(TradeUtil.buildTrade("1122|1|KLO|100|BUY|ACC-9045|NEW"));
			store.collectTrade(TradeUtil.buildTrade("1122|2|HJK|100|SELL|ACC-9045|AMEND"));
			store.collectTrade(TradeUtil.buildTrade("1122|3|KLO|100|SELL|ACC-9045|AMEND"));
			store.collectTrade(TradeUtil.buildTrade("1144|1|KLO|300|BUY|ACC-9045|NEW"));
			store.collectTrade(TradeUtil.buildTrade("1144|2|KLO|400|BUY|ACC-9045|AMEND"));
			store.collectTrade(TradeUtil.buildTrade("1155|1|KLO|600|SELL|ACC-9045|NEW"));
			store.collectTrade(TradeUtil.buildTrade("1155|2|KLO|0|BUY|ACC-9045|CANCEL"));
			Map<TradePositionIdentifier, TradePosition> tradeMap = store.getTradeCollection().getAllPositions();

			Comparator<TradePosition> compareByAccountNumber = (TradePosition o1, TradePosition o2) -> o1.getId()
					.getAccountNumber().compareTo(o2.getId().getAccountNumber());
			List tradeList = new LinkedList(tradeMap.values());
			Collections.sort(tradeList, compareByAccountNumber);

			for (Iterator iterator = tradeList.iterator(); iterator.hasNext();) {
				TradePosition tradePosition = (TradePosition) iterator.next();
				// tradePosition.getTrades().stream().sorted().forEach(System.out::print);

				logger.info("Account number: " + tradePosition.getId().getAccountNumber() + "|" + "instrument: "
						+ tradePosition.getId().getInstrument() + "|" + "Quantity: " + tradePosition.getQuantity() + "|"
						+ "Trades: " + tradePosition.getTrades().stream().sorted().collect(Collectors.toList()));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

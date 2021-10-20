package com.jpm.investment.trade.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.model.TradePosition;
import com.jpm.investment.trade.model.TradePositionIdentifier;

public class TradePositionsListImpl implements TradePostionsList {

	private final Map<TradePositionIdentifier, TradePosition> positions = new HashMap<TradePositionIdentifier, TradePosition>();
	private final Map<Integer, Trade> trades = new HashMap<Integer, Trade>();

	public Map<Integer, Trade> getAllTrades() {
		return trades;
	}

	public Trade getTradeById(Integer tradeId) {
		return trades.get(tradeId);
	}

	public void addTrade(Trade trade) {
		trades.put(trade.getTradeId(), trade);
	}

	public void addPosition(TradePosition position) {
		positions.put(position.getId(), position);
	}

	public TradePosition getPositionById(TradePositionIdentifier id) {
		return positions.get(id);
	}

	public Map<TradePositionIdentifier, TradePosition> getAllPositions() {
		return positions;
	}

	public List<TradePosition> getPositionsList() {
		return new LinkedList<TradePosition>(positions.values());
	}

	public TradePosition getPositionByTrade(Trade trade) {
		return positions.get(new TradePositionIdentifier(trade.getAccountNumber(), trade.getSecurityId()));
	}

	public TradePosition getPositionByTradeNewIfNotExists(Trade trade) {
		TradePosition p = getPositionByTrade(trade);
		if (null == p) {
			TradePositionIdentifier id = new TradePositionIdentifier(trade.getAccountNumber(), trade.getSecurityId());
			positions.put(id, p = new TradePosition(id));
		}
		return p;
	}

}

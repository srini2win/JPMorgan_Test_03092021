package com.jpm.investment.trade.util;

import java.util.List;
import java.util.Map;

import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.model.TradePosition;
import com.jpm.investment.trade.model.TradePositionIdentifier;

public interface TradePostionsList {
	public Map<Integer, Trade> getAllTrades();

	public Trade getTradeById(Integer tradeId);

	public void addTrade(Trade trade);

	public void addPosition(TradePosition position);

	public TradePosition getPositionById(TradePositionIdentifier id);

	public Map<TradePositionIdentifier, TradePosition> getAllPositions();

	public List<TradePosition> getPositionsList();

	public TradePosition getPositionByTrade(Trade trade);

	public TradePosition getPositionByTradeNewIfNotExists(Trade trade);
}
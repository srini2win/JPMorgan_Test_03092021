package com.jpm.investment.trade.commands;

import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.model.TradePosition;

public abstract class SumQuantityCommand extends QuantityCommand {
	public Integer execute(TradePosition position, Trade trade) {
		return position.getQuantity() + trade.getQuantity();
	}
}
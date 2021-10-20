package com.jpm.investment.trade.commands;

import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.model.TradePosition;

/*
 *  Abstract class for finding the diff between the trades
 */
public abstract class DiffQuantityCommand extends QuantityCommand {

	public Integer execute(TradePosition position, Trade trade) {
		return position.getQuantity() - trade.getQuantity();
	}

}
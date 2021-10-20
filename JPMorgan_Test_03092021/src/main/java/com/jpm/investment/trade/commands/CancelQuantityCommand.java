package com.jpm.investment.trade.commands;

import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.model.TradePosition;

/*
 *  Abstract class for finding the cancel/reversal
 */
public abstract class CancelQuantityCommand extends QuantityCommand {

	public Integer execute(TradePosition position, Trade trade) {
		return 0;
	}

}
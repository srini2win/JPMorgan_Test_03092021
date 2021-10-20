package com.jpm.investment.trade.commands;

import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.model.TradePosition;

/*
 *  Base interface for all the trade commands
 */
public interface TradeCalculatorCommand {
	public Integer doExecute(TradePosition position, Trade trade) throws Exception;
}

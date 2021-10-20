package com.jpm.investment.trade;

import com.jpm.investment.trade.commands.TradeCalculatorCommand;
import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.util.CommandFactory;
import com.jpm.investment.trade.util.Constants;

/*
 *  Based on the trade events the corresponding TradeCommand class will get initiated.
 */
public class TradeCommand {

	public static final TradeCalculatorCommand getTradeCommand(Trade trade) throws Exception {
		return CommandFactory
				.getCommand(trade.getDirection().name() + Constants.SEPERATOR + trade.getOperation().name());
	}

	public static final TradeCalculatorCommand getReversalTradeCommand(Trade trade) throws Exception {
		return CommandFactory.getCommand(trade.getDirection().name() + Constants.SEPERATOR + trade.getOperation().name()
				+ Constants.SEPERATOR + Constants.REVERSAL);
	}

}
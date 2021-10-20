package com.jpm.investment.trade.commands;

import com.jpm.investment.trade.model.Trade;
import com.jpm.investment.trade.model.TradePosition;

/*
 *  Trade validations
 */
public abstract class QuantityCommand implements TradeCalculatorCommand {

	public Integer doExecute(TradePosition position, Trade trade) throws Exception {
		validateTrade(trade);
		return execute(position, trade);
	}

	private void validateTrade(Trade trade) throws Exception {
		if (null == trade) {
			throw new Exception("Trade cannot be empty or null");
		}
		if (null == trade.getQuantity() || null == trade.getAccountNumber() || null == trade.getSecurityId()) {
			throw new Exception("Trade missing one of AccountNumber,SecurityId or Quantity Attributes");
		}
	}

	public abstract Integer execute(TradePosition position, Trade trade);
}
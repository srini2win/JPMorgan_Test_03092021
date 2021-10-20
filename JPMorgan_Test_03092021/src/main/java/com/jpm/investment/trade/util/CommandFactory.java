package com.jpm.investment.trade.util;

import com.jpm.investment.trade.commands.BuyAmendCommand;
import com.jpm.investment.trade.commands.BuyAmendReversalCommand;
import com.jpm.investment.trade.commands.BuyCancelCommand;
import com.jpm.investment.trade.commands.BuyNewCommand;
import com.jpm.investment.trade.commands.BuyNewReversalCommand;
import com.jpm.investment.trade.commands.SellAmendCommand;
import com.jpm.investment.trade.commands.SellAmendReversalCommand;
import com.jpm.investment.trade.commands.SellCancelCommand;
import com.jpm.investment.trade.commands.SellNewCommand;
import com.jpm.investment.trade.commands.SellNewReversalCommand;
import com.jpm.investment.trade.commands.TradeCalculatorCommand;

/*
 * Factory class for all command classes, based on the Trade operation and direction
 */
public class CommandFactory {

	public static TradeCalculatorCommand getCommand(String commandName) {
		if (commandName.equals(Constants.BUY_NEW))
			return new BuyNewCommand();
		else if (commandName.equals(Constants.BUY_AMEND))
			return new BuyAmendCommand();
		else if (commandName.equals(Constants.BUY_CANCEL))
			return new BuyCancelCommand();
		else if (commandName.equals(Constants.BUY_AMEND_REVERSAL))
			return new BuyAmendReversalCommand();
		else if (commandName.equals(Constants.BUY_NEW_REVERSAL))
			return new BuyNewReversalCommand();
		else if (commandName.equals(Constants.SELL_NEW))
			return new SellNewCommand();
		else if (commandName.equals(Constants.SELL_AMEND))
			return new SellAmendCommand();
		else if (commandName.equals(Constants.SELL_CANCEL))
			return new SellCancelCommand();
		else if (commandName.equals(Constants.SELL_AMEND_REVERSAL))
			return new SellAmendReversalCommand();
		else if (commandName.equals(Constants.SELL_NEW_REVERSAL))
			return new SellNewReversalCommand();

		return null;
	}

}

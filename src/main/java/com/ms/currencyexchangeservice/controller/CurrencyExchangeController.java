package com.ms.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ms.currencyexchangeservice.NumberToText;
import com.ms.currencyexchangeservice.bean.ExchangeValue;
import com.ms.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment env;
	
	@Autowired
	NumberToText toText;
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue
		(@PathVariable String from, @PathVariable String to){ 
		
		ExchangeValue exchangeValue=repository.findByFromAndTo(from, to);
		//ExchangeValue exchangeValue=new ExchangeValue(1000L,from,to,BigDecimal.valueOf(18));
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		logger.info("{}", exchangeValue);
		
		return exchangeValue;
	}
	
	@GetMapping("/number-to-text/{number}")
	public String convertToText(@PathVariable int number) {
		
		String textStr=toText.convert(number);
		
		return ""+number+" text representation is ="+textStr +" on port no "+ Integer.parseInt(env.getProperty("local.server.port"));
	}

}

package com.olive.process;

import org.springframework.batch.item.ItemProcessor;

public class MyItemProcessor implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		
		return null;
	}
	

}

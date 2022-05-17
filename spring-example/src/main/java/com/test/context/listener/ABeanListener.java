package com.test.context.listener;

import com.test.context.event.ABeanInitEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
@Component
public class ABeanListener implements ApplicationListener<ABeanInitEvent> {
	@Override
	public void onApplicationEvent(ABeanInitEvent event) {
		log.debug("event：[{}]",event.getClass().getSimpleName());
	}
}

package com.test.context.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * A这个bean正在被spring容器初始化
 */
public class ABeanInitEvent extends ApplicationContextEvent {

	public ABeanInitEvent(ApplicationContext source) {
		super(source);
	}
}

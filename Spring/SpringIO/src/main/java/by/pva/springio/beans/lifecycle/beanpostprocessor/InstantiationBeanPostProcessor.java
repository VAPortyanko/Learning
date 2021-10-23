package by.pva.springio.beans.lifecycle.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class InstantiationBeanPostProcessor implements BeanPostProcessor, Ordered {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("In postProcessBeforeInitialization method");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("In postProcessAfterInitialization method");
		if (bean instanceof IPayment) {
			((IPayment) bean).processInstance((IPayment) bean);
		}
		return bean;
	}

	@Override
	public int getOrder() {
		return 0;
	}
}

package module01.question13;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;


public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Arrays.stream(beanFactory.getBeanDefinitionNames())
                .map(beanFactory::getBeanDefinition)
                .filter(beanDefinition -> beanClassNameContains(beanDefinition, "module01.question13.beans"))
                .map(BeanDefinition::getBeanClassName)
                .forEach(System.out::println);
    }

    private boolean beanClassNameContains(BeanDefinition beanDefinition, String subString) {
        return beanDefinition.getBeanClassName() != null && beanDefinition.getBeanClassName().contains(subString);
    }
}

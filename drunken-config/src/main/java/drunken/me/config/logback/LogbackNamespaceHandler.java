package drunken.me.config.logback;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class LogbackNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("configurer", new LogbackBeanDefinitionParser());
    }
}


package drunken.me.config.logback;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class LogbackBeanDefinitionParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        final String configResourceAttribute = element.getAttribute(LogbackConfigurer.CONFIG_RESOURCE_KEY);
        final String environmentAttribute = element.getAttribute(LogbackConfigurer.ENVIRONMENT_KEY);
        final String intranetOrInternetAttribute = element.getAttribute(LogbackConfigurer.INTRA_OR_INTER_KEY);
        LogbackConfigurer.initLogging(configResourceAttribute, environmentAttribute, intranetOrInternetAttribute);
        return null;
    }


    private List<String> buildArgumentListForInitLogging(String... args) {
        return Arrays.asList(args);
    }

}

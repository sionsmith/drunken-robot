package drunken.me.config.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: c936557
 * Date: 11/10/13
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Constants {

    public static String CONTEXT;

    @Autowired
    private Constants(@Value("${site.url}") String context) {
        CONTEXT = context;
    }
}

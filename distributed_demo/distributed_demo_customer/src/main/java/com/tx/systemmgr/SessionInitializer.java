package com.tx.systemmgr;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created by tianxuan on 16/4/4.
 */
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {

    public SessionInitializer() {
        super(SessionConfig.class);
    }
}

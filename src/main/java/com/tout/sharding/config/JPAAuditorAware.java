package com.tout.sharding.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author xux
 * @date 2021-07-15 11:57
 **/
@Component
public class JPAAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Snowflake snowflake = IdUtil.getSnowflake();
        return Optional.of(snowflake.nextId());
    }
}

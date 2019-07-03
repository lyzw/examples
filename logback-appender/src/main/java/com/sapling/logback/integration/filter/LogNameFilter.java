package com.sapling.logback.integration.filter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * package filter
 * <p>
 * filter logging event by logger name
 * </p>
 *
 * @author weizhou
 * @version v1.0
 * @date 2019/3/14
 * @since v1.0
 */
public class LogNameFilter extends Filter<ILoggingEvent> {

    String[] packages;
    String level;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        System.out.println(event.getLoggerName());
        String className = event.getLoggerName();
        if (packages == null || packages.length == 0) {
            return FilterReply.DENY;
        }
        Level level2 = Level.toLevel(getLevel(), Level.ALL);
        List<String> list = Arrays.stream(packages).filter(f -> className.startsWith(f)).collect(Collectors.toList());
        if (list != null && list.size() > 0 &&
                event.getLevel().isGreaterOrEqual(level2)) {
            return FilterReply.ACCEPT;
        }
        return FilterReply.DENY;
    }

    public String[] getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages.split(",");
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

package com.fantasy.aop.objects.interfaces;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Created by Djelu on 02.09.2017.
 */
public interface Manager {

    Set<String> getExtensionList(String folder);
    Map<String, Integer> getExtensionCount(String folder);
}

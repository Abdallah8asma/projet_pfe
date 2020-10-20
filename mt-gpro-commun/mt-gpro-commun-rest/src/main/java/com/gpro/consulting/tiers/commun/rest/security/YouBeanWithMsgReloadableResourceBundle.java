package com.gpro.consulting.tiers.commun.rest.security;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class YouBeanWithMsgReloadableResourceBundle {
    public void yourMethod(){
    String msg = ms.getMessage("your.memo.nic", null, "your default message", Locale.CANADA);
}

@Autowired MessageSource ms;


}
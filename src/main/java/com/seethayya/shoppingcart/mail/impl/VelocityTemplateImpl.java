package com.seethayya.shoppingcart.mail.impl;

import com.seethayya.shoppingcart.mail.VelocityTemplate;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Anish
 * Date: 4/26/14
 * Time: 10:08 PM
 */
@Service
public class VelocityTemplateImpl implements VelocityTemplate {

    private VelocityEngine velocityEngine;

    public String prepareStringFromVelocityTemplate(String vmFileLocation, Object... data) {
        Map<String, Object> map = new HashMap<String, Object>();
        for(Object object : data) {
            map.put(WordUtils.uncapitalize(object.getClass().getSimpleName()), data);
        }
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, vmFileLocation, map);
        return text;
    }

    @Resource
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}

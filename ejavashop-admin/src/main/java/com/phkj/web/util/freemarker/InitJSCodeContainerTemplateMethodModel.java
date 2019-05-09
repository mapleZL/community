package com.phkj.web.util.freemarker;

import java.util.List;

import com.phkj.web.util.freemarkerutil.CodeManager;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class InitJSCodeContainerTemplateMethodModel implements TemplateMethodModel {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return CodeManager.getCodesJsonByDivs(arguments.toArray());
    }

}
